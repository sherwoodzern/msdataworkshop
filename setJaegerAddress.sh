#!/bin/bash

#It should not be necessary to run this file unless it took an extended period of time for the jaeger query service's loadbalancer to be provisisioned.

export WORKINGDIR=workingdir
echo WORKINGDIR = $WORKINGDIR

echo setting jaeger address...
kubectl get services jaeger-query -n msdataworkshop --output jsonpath='{.status.loadBalancer.ingress[0].ip}' > $WORKINGDIR/msdataworkshopjaegeraddress.txt

export MSDATAWORKSHOP_JAEGER_IP=$(cat $WORKINGDIR/msdataworkshopjaegeraddress.txt)
echo MSDATAWORKSHOP_JAEGER_IP... $MSDATAWORKSHOP_JAEGER_IP

if [[ $MSDATAWORKSHOP_JAEGER_IP == "" ]]
then
  echo "MSDATAWORKSHOP_JAEGER_IP does not exist. OKE may still be provisioning the loadbalancer. Try running ./setJaegerAddress.sh again or check the OCI console for progress."
fi

export JAEGER_QUERY_ADDRESS=http://$MSDATAWORKSHOP_JAEGER_IP
echo JAEGER_QUERY_ADDRESS = $JAEGER_QUERY_ADDRESS
echo $JAEGER_QUERY_ADDRESS > $WORKINGDIR/msdataworkshopjaegeraddress.txt