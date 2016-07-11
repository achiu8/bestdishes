(ns bestdishes.models.dish
  (:require [clojure.java.jdbc :as sql]
            [bestdishes.models.migration :refer [db]]))

(defn all []
  (into [] (sql/query db ["select * from dishes order by id desc"])))

(defn by-id [id]
  (first (sql/query db ["select * from dishes where id = ?" id])))

(defn by-restaurant-id [id]
  (into [] (sql/query db ["select * from dishes where restaurant_id = ?" id])))

(defn create [name restaurant_id]
  (sql/insert! db :dishes {:name name :restaurant_id restaurant_id}))
