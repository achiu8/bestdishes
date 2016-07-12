(ns bestdishes.controllers.locations
  (:require [compojure.core :refer [defroutes GET]]
            [bestdishes.views.locations :as view]
            [bestdishes.models.location :as location]
            [bestdishes.models.dish :as dish]))

(defn show [id]
  (let [location (location/by-id id)
        dishes   (dish/by-location-id id)]
    (view/show (assoc location :dishes dishes))))

(defroutes routes
  (GET  "/locations/:id" [id] (show (Integer. id))))
