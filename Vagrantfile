# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|
  config.vm.define "ldap" do |ldap|
    ldap.vm.box = "ubuntu/trusty64" # xenial
    ldap.vm.hostname = "testldap.springframework.org"

    ldap.vm.network "private_network", ip: "192.168.56.27"

    ldap.vm.provision "shell", inline: <<-SHELL
      debconf-set-selections <<< 'slapd slapd/password1 password d3v1t!'
      debconf-set-selections <<< 'slapd slapd/password2 password d3v1t!'
      apt-get update -q
      apt-get install -y -q slapd ldap-utils
      ldapadd -x -D cn=admin,dc=springframework,dc=org -w d3v1t! -f /vagrant/test/test-server.ldif
    SHELL
  end

  config.vm.define "web" do |web|
    web.vm.box = "ubuntu/trusty64"
    web.vm.hostname = "testbsllogin.christiancarey.com"

    web.vm.network "private_network", ip: "192.168.56.28"

    # Provision apache and link the web directory
    config.vm.provision "shell", inline: <<-SHELL
      debconf-set-selections <<< 'mysql-server mysql-server/root_password password d3v1t!'
      debconf-set-selections <<< 'mysql-server mysql-server/root_password_again password d3v1t!'
      apt-get -q update
      apt-get install -y -q apache2 mysql-server npm nodejs-legacy

      sed -i -e 's/127\.0\.0\.1/192\.168\.56\.28/g' /etc/mysql/my.cnf
      service mysql restart

      echo "[client]" > ~/.my.cnf
      echo "password=d3v1t!" >> ~/.my.cnf
      chmod 600 ~/.my.cnf
      echo "[client]" > /home/vagrant/.my.cnf
      echo "password=d3v1t!" >> /home/vagrant/.my.cnf
      chown vagrant:vagrant /home/vagrant/.my.cnf
      chmod 600 /home/vagrant/.my.cnf

      ln -s /vagrant/web /var/www/html/bsllogin
      ln -s /vagrant/reverse-proxy.conf /etc/apache2/sites-enabled/bsl-api.conf
      service apache2 restart

      /vagrant/scripts/create_db.sh bsl bsluser test123
      cd /vagrant
      npm install --quiet jslint
    SHELL
  end
end
