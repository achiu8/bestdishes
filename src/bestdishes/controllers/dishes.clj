(ns bestdishes.controllers.dishes
  (:require [compojure.core :refer [defroutes GET POST]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [bestdishes.views.dishes :as view]
            [bestdishes.models.dish :as dish]
            [bestdishes.models.restaurant :as restaurant]
            [bestdishes.models.location :as location]
            [bestdishes.models.review :as review]))

(defn index [params]
  (view/index (dish/all)
              (restaurant/all)
              (location/all)))

(defn show [id]
  (let [dish       (dish/by-id id)
        restaurant (restaurant/by-id (:restaurant_id dish))
        location   (location/by-id (:location_id restaurant))
        reviews    (review/by-dish-id id)]
    (view/show (assoc dish
                 :restaurant_name (:name restaurant)
                 :location_id     (:id location)
                 :location_city   (:city location)
                 :reviews         reviews))))

(defn create [name restaurant_id]
  (when-not (str/blank? name)
    (dish/create name restaurant_id))
  (ring/redirect "/"))

(defroutes routes
  (GET  "/" {params :params} (index params))
  (POST "/" [name restaurant_id] (create name (Integer. restaurant_id)))
  (GET  "/dishes/:id" [id] (show (Integer. id))))
