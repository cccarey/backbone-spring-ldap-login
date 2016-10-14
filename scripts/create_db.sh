#!/usr/bin/env bash

DBNAME=""
DBUSER=""
DBPASSWD=""
DBPASSCHK="check"
WITH_PASSWD="-p"

[[ -z "$1" ]] || DBNAME="$1"
[[ -z "$2" ]] || DBUSER="$2"
[[ -z "$3" ]] || {
    DBPASSWD="$3"
    DBPASSCHK="$3"
}
[[ -z "$4" ]] || echo "--> $4 <--"
[[ -f ~/.my.cnf ]] && WITH_PASSWD=""

[[ -z "$DBNAME" ]] && {
    echo -n "Enter db name    : "
    read DBNAME
}
[[ -z "$DBUSER" ]] && {
    echo -n "Enter db user    : "
    read DBUSER
}

while [ "$DBPASSWD" != "$DBPASSCHK" ]; do
    echo -n "Enter db password: "
    read -s DBPASSWD
    echo

    echo -n "Re-enter password: "
    read -s DBPASSCHK
    echo
done

echo "creating $DBNAME database with user $DBUSER..."
[[ -z "$WITH_PASSWD" ]] || echo -n "MySQL root user -> "
mysql -u root $WITH_PASSWD << END
    create database if not exists $DBNAME;
    grant all privileges on $DBNAME.* to '$DBUSER'@'localhost' identified by '$DBPASSWD' with grant option;
    grant all privileges on $DBNAME.* to '$DBUSER'@'192.168.56.0/255.255.255.0' identified by '$DBPASSWD' with grant option;
    flush privileges;
END
