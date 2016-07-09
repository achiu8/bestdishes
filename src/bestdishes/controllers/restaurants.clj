(ns bestdishes.controllers.restaurants
  (:require [compojure.core :refer [defroutes GET POST]]
            [bestdishes.views.restaurants :as view]
            [bestdishes.models.restaurant :as restaurant]
            [bestdishes.models.dish :as dish]))

(defn show [id]
  (let [restaurant (restaurant/by-id id)
        dishes     (dish/by-restaurant-id id)]
    (view/show (assoc restaurant :dishes dishes))))

(defroutes routes
  (GET "/restaurants/:id" [id] (show (Integer. id))))
