(ns bestdishes.web
  (:require [compojure.core :refer [defroutes]]
            [compojure.route :as route]
            [ring.adapter.jetty :as ring]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [bestdishes.controllers.dishes :as dishes]
            [bestdishes.controllers.restaurants :as restaurants]
            [bestdishes.controllers.reviews :as reviews]
            [bestdishes.controllers.locations :as locations]
            [bestdishes.views.layout :as layout]
            [bestdishes.models.migration :as db])
  (:gen-class))

(defroutes routes
  dishes/routes
  restaurants/routes
  reviews/routes
  locations/routes
  (route/resources "/")
  (route/not-found (layout/four-oh-four)))

(def application (wrap-defaults routes site-defaults))

(defn start [port]
  (ring/run-jetty application {:port port :join? false}))

(defn -main []
  (db/migrate)
  (let [port (Integer. (or (System/getenv "PORT") "8080"))]
    (start port)))
