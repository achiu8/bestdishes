(ns bestdishes.models.location
  (:require [clojure.java.jdbc :as sql]
            [bestdishes.models.migration :refer [db]]))

(defn all []
  (into [] (sql/query db ["select * from locations order by id desc"])))
