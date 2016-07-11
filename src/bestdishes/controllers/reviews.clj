(ns bestdishes.controllers.reviews
  (:require [compojure.core :refer [defroutes GET POST]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [bestdishes.models.review :as review]))

(defn create [dish_id rating content]
  (when-not (or (nil? dish_id) (nil? rating) (str/blank? content))
    (review/create dish_id rating content))
  (ring/redirect (str "/dishes/" dish_id)))

(defroutes routes
  (POST "/reviews" [dish_id rating content] (create (Integer. dish_id) (Integer. rating) content)))
