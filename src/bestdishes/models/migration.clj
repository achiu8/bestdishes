(ns bestdishes.models.migration
  (:require [clojure.java.jdbc :as sql]))

(def db (or (System/getenv "DATABASE_URL")
            "postgresql://localhost:5432/bestdishes"))

(def tables
  {:locations   [[:id            :serial    "primary key"]
                 [:city          :varchar   "not null"]
                 [:state         :varchar   "not null"]]
   :restaurants [[:id            :serial    "primary key"]
                 [:location_id   :integer   "not null" "references locations (id)"]
                 [:name          :varchar   "not null"]
                 [:created_at    :timestamp "not null" "default current_timestamp"]]
   :dishes      [[:id            :serial    "primary key"]
                 [:restaurant_id :integer   "not null" "references restaurants (id)"]
                 [:name          :varchar   "not null"]
                 [:created_at    :timestamp "not null" "default current_timestamp"]]
   :reviews     [[:id            :serial    "primary key"]
                 [:dish_id       :integer   "not null" "references dishes (id)"]
                 [:rating        :integer   "not null"]
                 [:content       :text      "not null"]
                 [:created_at    :timestamp "not null" "default current_timestamp"]]
   :categories  [[:id            :serial    "primary key"]
                 [:name          :varchar   "not null"]
                 [:created_at    :timestamp "not null" "default current_timestamp"]]})

(defn existing-tables []
  (->> (sql/query db
                  [(str "select table_name from information_schema.tables "
                        "where table_schema='public'")])
       (map :table_name)
       (into #{})))

(defn exists? [table]
  ((existing-tables) (name table)))

(defmacro log [name action]
  `(do
     (print ~name)
     (flush)
     ~action
     (println " done")))

(defn migrate []
  (let [table-specs (remove #(exists? (first %)) tables)
        commands    (map #(apply sql/create-table-ddl %) table-specs)]
    (log "Creating tables..."
         (sql/db-do-commands db commands))))

(defn drop-tables []
  (let [table-names (clojure.string/join #", " (map name (filter exists? (keys tables))))]
    (log "Dropping tables..."
         (sql/db-do-commands db (str "drop table " table-names " cascade")))))

(defn reset []
  (drop-tables)
  (migrate))
