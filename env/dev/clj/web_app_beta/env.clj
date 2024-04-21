(ns web-app-beta.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [web-app-beta.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[web-app-beta started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[web-app-beta has shut down successfully]=-"))
   :middleware wrap-dev})
