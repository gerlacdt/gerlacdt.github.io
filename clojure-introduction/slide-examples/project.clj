(defproject slide-examples "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot slide-examples.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :repl {:plugins [[cider/cider-nrepl "0.13.0-SNAPSHOT"]]}
             :dev {:dependencies [[org.clojure/tools.nrepl "0.2.12"]
                                  [org.clojure/tools.trace "0.7.9"]]}})
