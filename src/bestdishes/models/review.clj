(ns bestdishes.models.review
  (:require [clojure.java.jdbc :as sql]
            [bestdishes.models.migration :refer [db]]))

(defn by-dish-id [id]
  (into [] (sql/query db ["select * from reviews where dish_id = ?" id])))

(defn create [dish_id rating content]
  (sql/insert! db :reviews {:dish_id dish_id :rating rating :content content}))
