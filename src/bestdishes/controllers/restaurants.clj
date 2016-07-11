(ns bestdishes.controllers.restaurants
  (:require [compojure.core :refer [defroutes GET POST]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [bestdishes.views.restaurants :as view]
            [bestdishes.models.restaurant :as restaurant]
            [bestdishes.models.dish :as dish]))

(defn show [id]
  (let [restaurant (restaurant/by-id id)
        dishes     (dish/by-restaurant-id id)]
    (view/show (assoc restaurant :dishes dishes))))

(defn create [name location_id]
  (when-not (or (str/blank? name) (nil? location_id))
    (restaurant/create name location_id))
  (ring/redirect "/"))

(defroutes routes
  (GET  "/restaurants/:id" [id] (show (Integer. id)))
  (POST "/restaurants" [name location_id] (create name (Integer. location_id))))
