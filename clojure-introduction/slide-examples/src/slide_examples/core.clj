(ns slide-examples.core)

;; this is a comment

;; creates a global var
(def x 1)

(def hello-1 (fn []
               "Hello World!"))

;; creates a global var with a function as value
(defn hello-2
  "Returns hello world"
  []
  "Hello World!")

(defn hello-3
  "Multi arity function."
  ([] "Hello world!")
  ([name] (str "Hello " name "!")))


(defn add-1
  "Sum up given parameters."
  [& more]
  (apply + more))

(defn destruct-1
  "Extract firstname and lastname and returns fullname."
  [{:keys [:firstname :lastname]}]
  {:fullname (str firstname " " lastname)})

(defn destruct-2
  "Extracts firstname and lastname but also add defaults if one of the
  names do not exist. Returns concatenated fullname string."
  [{:keys [:firstname :lastname]
    :or {:firstname "John" :lastname "Doe"}}]
  {:fullname (str firstname " " lastname)})

;; if and cond

(defn my-even?
  "Returns true if number is even."
  [n]
  (if (even? n)
    true
    false))

(defn greeting
  "Returns the greeting in the given language. Defaults to :english"
  ([language]
   (cond (= language :english) "Hello"
         (= language :french) "Bonjour"
         (= language :spanish) "Hola"
         :else "Hello"))
  ([] "Hello"))

;; let

(defn area-circle
  "Returns the area of a circle with the given radius"
  [radius]
  (let [pi 3.1415
        radius-squared (* radius radius)]
    (* pi radius-squared)))

;; loops (better use high-order functions like
;; map, filter, reduce)
(defn print-list-1
  "Prints all entries of given list "
  [collection]
  (loop [coll collection]
    (if (empty? coll)
      nil
      (do
        (println (first coll))
        (recur (rest coll))))))

(defn print-list-2
  "Prints all entries of given list"
  [collection]
  (doseq [entry collection]
    (println entry)))

;; data structures

(def a-vector [1 2 3])
(conj a-vector 4)

(def a-list '(1 2 3))
(conj a-list 4)

(def a-set #{1 2 3})
(conj a-set 4)

(def a-map {:foo "bar" :a 1})
(assoc a-map :b 2)


;; lazy data structures
(def natural-numbers (iterate inc 1))
;; (take 5 natural-numbers)

;; map filter reduce
(def even-numbers (filter even? natural-numbers))

(defn sum-of-squares
  "Returns the sum of the given numbers squared."
  [& numbers]
  (reduce + 0  (map (fn [x]
                      (* x x))
                    numbers)))

;; partial
(def even-numbers-2 (iterate (partial + 2) 0))
;; (take 10 even-numbers-2)

;; concurrency refs (in-memory transactions)
(def account-a (ref 1000))
(def account-b (ref 0))

(defn transfer-money
  "Transfers the given amount of money from account a to account b"
  [amount a b]
  (dosync
   (alter a #(- % amount))
   (alter b #(+ % amount))))
;; @account-a
;; @account-b
;; (transfer-money 100 account-a account-b)

;; for-each macro
(defmacro for-each
  "Simulates the java for-each loop"
  [[sym coll] & body]
  `(loop [coll# ~coll]
     (when-let [[~sym & xs#] (seq coll#)]
       ~@body
       (recur xs#))))

;; (for-each [x [1 2 3]]
;;           (println x))
