export VUS="$1"

set -e

if [[ ! $LB =~ ^[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}$ ]]; then
    echo "Set variable LB to the external IP address of the ext-order service"
    exit
fi

NEXT_RUN_FILE=next_run

if [ ! -f "$NEXT_RUN_FILE" ]; then
    echo 0 > $NEXT_RUN_FILE
fi

#export RUN=`cat $NEXT_RUN_FILE`
#RUN=$((RUN+1))
#echo $RUN > $NEXT_RUN_FILE

#echo Warming up for 30 seconds
#./k6 run --vus $VUS --duration "20s" --address localhost:6566 placeorder.js > /dev/null

#sleep 10

export RUN=`cat $NEXT_RUN_FILE`
RUN=$((RUN+1))
echo $RUN > $NEXT_RUN_FILE

./k6 run --vus 20 --duration "30s" --address localhost:6566 placeorder.js