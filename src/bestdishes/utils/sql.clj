(ns bestdishes.utils.sql
  (:require [clojure.java.jdbc :as j]
            [bestdishes.models.migration :refer [db]]))

(def query (partial j/query db))

(def query-first (comp first query))

(def query-all (comp (partial into []) query))

(def insert (partial j/insert! db))
