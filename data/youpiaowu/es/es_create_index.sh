#!/bin/bash

curl -XPUT -H 'Content-Type: application/json' --data @es_index_setting.json  http://192.168.7.7:9200/stamp 
