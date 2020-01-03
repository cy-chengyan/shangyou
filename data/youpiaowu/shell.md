'''shell
cat recom.txt|grep "REDIS:"|awk -F'\t' '{print $2;}' > recom_redis.script
'''
