(ns bestdishes.views.layout
  (:require [hiccup.page :as h]))

(defn common [& body]
  (h/html5
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:http-equiv "X-UA-Compatible" :content "IE-edge,chrome=1"}]
    [:meta {:name    "viewport"
            :content "width=device-width, initial-scale=1, maximum-scale=1"}]
    [:title "bestdishes"]]
   [:body
    [:div {:id "header"}
     [:h1 {:class "container"}
      [:a {:href "/"} "bestdishes"]]]
    [:div {:id "content" :class "container"} body]]))

(defn four-oh-four []
  (common [:div {:id "four-oh-four"}
           "The page you requested could not be found"]))
