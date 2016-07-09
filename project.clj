(defproject bestdishes "0.1.0-SNAPSHOT"
  :description "best dishes app"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/java.jdbc "0.6.1"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [ring/ring-jetty-adapter "1.5.0"]
                 [ring/ring-defaults "0.1.2"]
                 [compojure "1.5.1"]
                 [hiccup "1.0.5"]]
  :main ^:skip-aot bestdishes.web
  :uberjar-name "bestdishes-standalone.jar"
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler bestdishes.web/application
         :init bestdishes.models.migration/migrate}
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring-mock "0.1.5"]]}
             :uberjar {:aot :all}})
