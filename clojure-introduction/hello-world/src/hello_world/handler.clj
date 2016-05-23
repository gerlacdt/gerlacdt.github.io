(ns hello-world.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.util.response :refer [response]]
            [cheshire.core :refer :all]
            [io.aviso.exception :as aviso-ex]
            [taoensso.timbre :as timbre
             :refer (log  trace  debug  info  warn  error  fatal  report
                          logf tracef debugf infof warnf errorf fatalf reportf
                          spy get-env log-env)]))

(defn json-output-fn
  [{:keys [?err_ vargs_ hostname_ timestamp_ level] :as args}]
  (let [messages (map (fn [msg] { :timestamp @timestamp_
                                 :level     level
                                 :hostname  @hostname_
                                 :message   msg })
                      @vargs_)
        err (force ?err_)
        json-messages (map #(generate-string %) messages)]
    ;; (print "print default-fn-output" err)
    (if err
      (clojure.string/join "\n" (conj json-messages {:ex (aviso-ex/format-exception err)}))
      (clojure.string/join "\n" (conj json-messages)))))

;; (error (Exception. "my exception message") "text message")

;; (timbre/merge-config!
;;  {:appenders {:println {:output-fn json-output-fn}}})

(timbre/merge-config!
 {:appenders {:println {:output-fn timbre/default-output-fn}}})

(def users [{:id "1" :firstname "foo" :lastname "bar"}
            {:id "2" :firstname "john" :lastname "doe"}
            {:id "3" :firstname "daniel" :lastname "gerlach"}])

(defn health
  "Returns ring response health check"
  []
  (info "health check OK")
  (response {:status "OK"}))

(defn get-users
  "Returns all users"
  []
  (response users))

(defn get-user
  "Return user with given id"
  [id]
  (let [user (first (filter (fn [user]
                              (when (= (:id user) id)
                                user))
                            users))]
    (info user)
    (if user
      (response user)
      (route/not-found (response {:message "Not found"})))))

(defn duration
  "Returns duration in seconds. Input params should be System/nanoTime"
  [start]
  (float (/ (- (System/nanoTime) start) 1000000)))

(defn wrap-response-time
  "Logs duration of response time"
  [handler]
  (fn [request]
    (let [start (System/nanoTime)
          response (handler request)]
      (info {:message "response time"
             :url (:uri request)
             :response-time-sec (duration start)})
      response)))

(defroutes handler
  (GET "/health" [] (health))
  (GET "/users" {params :params} (get-users))
  (GET "/users/:id" {{id :id} :params} (get-user id))
  (route/not-found (response {:message "Not Found"})))

(def app
  (-> handler
      wrap-response-time
      wrap-params
      wrap-json-body
      wrap-json-response))
