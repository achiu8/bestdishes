(ns bestdishes.models.review
  (:require [bestdishes.utils.sql :as sql]))

(defn by-dish-id [id]
  (sql/query-all ["select * from reviews where dish_id = ?" id]))

(defn create [dish_id rating content]
  (sql/insert :reviews {:dish_id dish_id :rating rating :content content}))
