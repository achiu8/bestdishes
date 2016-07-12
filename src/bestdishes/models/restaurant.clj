(ns bestdishes.models.restaurant
  (:require [bestdishes.utils.sql :as sql]))

(defn all []
  (sql/query-all ["select * from restaurants order by id desc"]))

(defn by-id [id]
  (sql/query-first ["select * from restaurants where id = ?" id]))

(defn create [name location_id]
  (sql/insert :restaurants {:name name :location_id location_id}))
