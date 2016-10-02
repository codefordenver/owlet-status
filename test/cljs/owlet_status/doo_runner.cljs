(ns owlet-status.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [owlet-status.core-test]))

(doo-tests 'owlet-status.core-test)

