#!/bin/bash
dirname=`dirname $0`
#file=${dirname}/../applications/ercore.ear/ecom/er2.properties
file=${dirname}/../environment/token/env.properties
param=priceplan.version.reread.flag

if [ ! -f ${file} ]; then 
echo "trying to update the priceplan reread flag in ${file} but couldn't find it"
exit 1
fi

trim() { echo $1; }

value=`grep $param $file|awk -F'=' '{print $2}'`
value=$(trim ${value})
let newvalue=value+1 2>/dev/null
#bash can't handle floating points in this simple arithmetic
if [ ! $? -eq 0 ]; then
newvalue=2
fi

echo "updating $param from $value to $newvalue"
sed "s%^$param[ ]*=.*$%$param=$newvalue%" $file >/var/tmp/file.tmp.$USER
cp /var/tmp/file.tmp.$USER $file
rm /var/tmp/file.tmp.$USER
