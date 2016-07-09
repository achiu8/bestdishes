(ns bestdishes.models.restaurant
  (:require [clojure.java.jdbc :as sql]
            [bestdishes.models.migration :refer [db]]))

(defn by-id [id]
  (first (sql/query db ["select * from restaurants where id = ?" id])))

