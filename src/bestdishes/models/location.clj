(ns bestdishes.models.location
  (:require [bestdishes.utils.sql :as sql]))

(defn all []
  (sql/query-all ["select * from locations order by id desc"]))

(defn by-id [id]
  (sql/query-first ["select * from locations where id = ?" id]))
