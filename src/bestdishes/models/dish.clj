(ns bestdishes.models.dish
  (:require [bestdishes.utils.sql :as sql]))

(defn all []
  (sql/query-all ["select * from dishes order by id desc"]))

(defn by-id [id]
  (sql/query-first ["select * from dishes where id = ?" id]))

(defn by-restaurant-id [id]
  (sql/query-all ["select * from dishes where restaurant_id = ?" id]))

(defn by-location-id [id]
  (sql/query-all [(str "select d.* from dishes d join restaurants r "
                       "on d.restaurant_id = r.id where r.location_id = ?")
                  id]))

(defn create [name restaurant_id]
  (sql/insert :dishes {:name name :restaurant_id restaurant_id}))
