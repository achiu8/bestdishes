(ns bestdishes.models.seed
  (:require [clojure.java.jdbc :as sql]
            [bestdishes.models.migration :refer [db]]))

(def restaurants ["Nama" "707 Sutter" "Little Skillet" "Pazzia" "Grand Harbor"])

(def dishes
  [{:name "Kimchi Tonkotsu Ramen"
    :restaurant "Nama"}
   {:name "Budae Jigae"
    :restaurant "707 Sutter"}
   {:name "Yukaejang"
    :restaurant "707 Sutter"}
   {:name "Corn Cheese"
    :restaurant "707 Sutter"}
   {:name "Chicken and Waffles"
    :restaurant "Little Skillet"}
   {:name "Pizza Napoletana"
    :restaurant "Pazzia"}
   {:name "Penne Puttanesca"
    :restaurant "Pazzia"}
   {:name "Beef Noodle Soup"
    :restaurant "Grand Harbor"}])

(defn get-id-for-restaurant [name]
  (-> (sql/query db ["select id from restaurants where name = ?" name])
      first :id))

(defn seed-restaurants []
  (doseq [restaurant restaurants]
    (sql/insert! db :restaurants {:name restaurant})))

(defn seed-dishes []
  (doseq [dish dishes]
    (let [restaurant_id (get-id-for-restaurant (:restaurant dish))]
      (sql/insert! db :dishes {:name (:name dish) :restaurant_id restaurant_id}))))

(defn seed-categories []
  (let [categories ["Japanese" "Korean" "American" "Italian" "Chinese"]]
    (doseq [category categories]
      (sql/insert! db :categories {:name category}))))

(defn seed-all []
  (seed-restaurants)
  (seed-dishes)
  (seed-categories))
