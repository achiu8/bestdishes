(ns bestdishes.models.restaurant
  (:require [clojure.java.jdbc :as sql]
            [bestdishes.models.migration :refer [db]]))

(defn all []
  (into [] (sql/query db ["select * from restaurants order by id desc"])))

(defn by-id [id]
  (first (sql/query db ["select * from restaurants where id = ?" id])))

(defn create [name location_id]
  (sql/insert! db :restaurants {:name name :location_id location_id}))
