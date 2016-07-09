(ns bestdishes.views.dishes
  (:require [bestdishes.views.layout :as layout]
            [hiccup.core :refer [h]]
            [hiccup.form :as form]
            [ring.util.anti-forgery :as anti-forgery]))

(defn dish-form []
  [:div {:id "dish-form"}
   (form/form-to [:post "/"]
                 (anti-forgery/anti-forgery-field)
                 (form/label "dish" "What's the name of the dish?")
                 (form/text-field "dish")
                 (form/submit-button "Save"))])

(defn display-dishes [dishes]
  [:div
   (map (fn [dish]
          [:p [:a {:href (str "/dishes/" (:id dish))} (h (:name dish))]])
        dishes)])

(defn display-dish [dish]
  [:div
   [:h3 (h (:name dish))]
   [:a {:href (str "/restaurants/" (:restaurant_id dish))} (h (:restaurant_name dish))]])

(defn index [dishes]
  (layout/common "bestdishes"
                 (dish-form)
                 (display-dishes dishes)))

(defn show [dish]
  (layout/common "bestdishes"
                 (display-dish dish)))
