(ns bestdishes.models.seed
  (:require [bestdishes.utils.sql :as sql]))

(def locations
  [{:city "San Francisco"
    :state "CA"}
   {:city "Fremont"
    :state "CA"}
   {:city "New York"
    :state "NY"}])

(def restaurants
  [{:name "Nama"
    :city "San Francisco"}
   {:name "707 Sutter"
    :city "San Francisco"}
   {:name "Little Skillet"
    :city "San Francisco"}
   {:name "Pazzia"
    :city "San Francisco"}
   {:name "Grand Harbor"
    :city "Fremont"}])

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

(defn get-id-for-location [city]
  (:id (sql/query-first ["select id from locations where city = ?" city])))

(defn get-id-for-restaurant [name]
  (:id (sql/query-first ["select id from restaurants where name = ?" name])))

(defn seed-locations []
  (doseq [location locations]
    (sql/insert :locations location)))

(defn seed-restaurants []
  (doseq [restaurant restaurants]
    (let [location_id (get-id-for-location (:city restaurant))]
      (sql/insert :restaurants {:name (:name restaurant) :location_id location_id}))))

(defn seed-dishes []
  (doseq [dish dishes]
    (let [restaurant_id (get-id-for-restaurant (:restaurant dish))]
      (sql/insert :dishes {:name (:name dish) :restaurant_id restaurant_id}))))

(defn seed-categories []
  (let [categories ["Japanese" "Korean" "American" "Italian" "Chinese"]]
    (doseq [category categories]
      (sql/insert :categories {:name category}))))

(defn seed-all []
  (seed-locations)
  (seed-restaurants)
  (seed-dishes)
  (seed-categories))
