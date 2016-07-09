(ns bestdishes.controllers.dishes
  (:require [compojure.core :refer [defroutes GET POST]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [bestdishes.views.dishes :as view]
            [bestdishes.models.dish :as dish]
            [bestdishes.models.restaurant :as restaurant]))

(defn index []
  (view/index (dish/all)))

(defn show [id]
  (let [dish       (dish/by-id id)
        restaurant (restaurant/by-id (:restaurant_id dish))]
    (view/show (assoc dish :restaurant_name (:name restaurant)))))

(defn create [dish]
  (when-not (str/blank? dish)
    (dish/create dish))
  (ring/redirect "/"))

(defroutes routes
  (GET  "/" [] (index))
  (POST "/" [dish] (create dish))
  (GET  "/dishes/:id" [id] (show (Integer. id))))
