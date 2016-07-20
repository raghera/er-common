#!/bin/ksh
#############################################################################################
#
# Author     : manuel.conde@rightitservices.com 
#                       on behalf of Vodafone Group - Global Platforms & Services
#
# Description: Script responsible for the instalation of server 
#              certificates into a truststore.
#
#
#            $ install_cert.sh
#
#            To run in batch mode, execute with arguments.
#
#                For example:
#
#                $ install_cert.sh -h <https host> -p <https port> -s <passphrase> -f <truststore file>
#                $ install_cert.sh -h 145.230.58.68 -p 8443 -s changeit -f /home/condem/CR1425/ssl/myCAcerts1/participant/myERCoreTruststore.jks
#
#
# $Id: install_cert.sh,v 1.1 2015/01/05 16:51:48 matt.darwin Exp $
##############################################################################################

##############################################################
##     C O N S T A N T S   S E C T I O N
##############################################################
NAME_="$0"
PURPOSE_="Installs server's certificate into a given truststore"
SYNOPSIS_="$NAME_ -h <https host> -p <https port> -s <passphrase> -f <truststore file>"

##############################################################
##     C O N F I G     S E C T I O N
##############################################################
JAVA_APP_JAR=ER_CORE_api.jar
JAR_LOCATION=../applications/ercore.ear/APP-INF/lib
JAVA_MAIN_CLASS=com.vodafone.global.er.util.InstallCert
JAVA=$JAVA_HOME/bin/java

##############################################################
##     V A R I A B L E S   S E C T I O N
##############################################################
v_server=''
v_port=''
v_passphrase=''
v_truststore=''
v_confirm='Y'
v_args_total=0

##############################################################
##     F U N C T I O N S     S E C T I O N
##############################################################
function usage 
{

echo >&2 ""
echo >&2 "$NAME_ - $PURPOSE_
Usage: $SYNOPSIS_
Required:
    -h host to get certificate from
    -p https port of the host to get certificate from
    -s passphrase of the local truststore
    -f truststore file to install certificate to 
Optional:
    -u usage and options (this help)

"
    exit 1
}

#############################################################################
####                                                                     ####
####                             MAIN ROUTINE                            ####
####                                                                     ####
#############################################################################


v_args_total=$#

while getopts h:p:s:f:u OPTION 
do
    case ${OPTION} in
        h) v_server=$OPTARG;;
        p) v_port=$OPTARG;;
        s) v_passphrase=$OPTARG;;
        f) v_truststore=$OPTARG;;
        ?) usage;;
      \?)  usage 
           exit 2;;
    esac
done
shift $(( $OPTIND - 1 ))

#[ $# -ne 8 ] && { echo >&2 wrong number of arguments, type $NAME_ -u for help; exit 1; }


#Check
[ $v_server ] || { echo >&2 host is required, type $NAME_ -u for help; exit 1; }
[ $v_port ] || { echo >&2 https port is required, type $NAME_ -u for help; exit 1; }
[ $v_passphrase ] || { echo >&2 passphrase is required, type $NAME_ -u for help; exit 1; }
[ $v_truststore ] || { echo >&2 truststore is required, type $NAME_ -u for help; exit 1; }
[ -f $v_truststore ]  || { echo >&2 truststore $v_truststore does not exist, type $NAME_ -u for help; exit 1; } 


$JAVA -cp $JAR_LOCATION/$JAVA_APP_JAR $JAVA_MAIN_CLASS $v_server:$v_port $v_passphrase $v_truststore

exit $?

