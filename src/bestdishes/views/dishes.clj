(ns bestdishes.views.dishes
  (:require [hiccup.core :refer [h]]
            [hiccup.form :as form]
            [ring.util.anti-forgery :as anti-forgery]
            [bestdishes.views.layout :as layout]
            [bestdishes.views.reviews :as reviews]))

(defn dish-form [restaurants]
  (let [restaurant-options (->> restaurants
                                (map (fn [restaurant] (map #(% restaurant) [:name :id])))
                                (sort-by first))]
    [:div {:id "dish-form"}
     [:h4 "Add a Dish"]
     (form/form-to [:post "/"]
                   (anti-forgery/anti-forgery-field)
                   (form/label :restaurant_id "What restaurant?")
                   (form/drop-down :restaurant_id restaurant-options)
                   (form/label :name "What's the name of the dish?")
                   (form/text-field :name)
                   (form/submit-button "Save"))]))

(defn restaurant-form [locations]
  (let [location-options (->> locations
                              (map (fn [location] (map #(% location) [:city :id])))
                              (sort-by first))]
    [:div {:id "restaurant-form"}
     [:h4 "Add a Restaurant"]
     (form/form-to [:post "/restaurants"]
                   (anti-forgery/anti-forgery-field)
                   (form/label :location_id "Where is it?")
                   (form/drop-down :location_id location-options)
                   (form/label :name "What's the name of the restaurant?")
                   (form/text-field :name)
                   (form/submit-button "Save"))]))

(defn display-dishes [dishes]
  [:div
   (map (fn [dish]
          [:p
           [:a {:href (str "/dishes/" (:id dish))}
            (h (:name dish))]])
        dishes)])

(defn display-dish [dish]
  [:div
   [:h3 (h (:name dish))]
   [:p
    [:a {:href (str "/restaurants/" (:restaurant_id dish))}
     (h (:restaurant_name dish))]]
   [:p
    [:a {:href (str "/locations/" (:location_id dish))}
     (h (:location_city dish))]]
   (reviews/display-reviews (:reviews dish))
   (reviews/review-form (:id dish))])

(defn index [dishes restaurants locations]
  (layout/common (dish-form restaurants)
                 (restaurant-form locations)
                 (display-dishes dishes)))

(defn show [dish]
  (layout/common (display-dish dish)))
