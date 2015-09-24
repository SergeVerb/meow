# meow

## Download the app

* For Ubuntu and other distributions with debian packaging 

https://github.com/pnmougel/meow/raw/master/release/meow_1.0_all.deb

* Rpm 

## Build the application

You must have openjdk8, git, and sbt installed.

```
sudo apt-get install openjdk-8-jdk git
echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 642AC823
sudo apt-get update
sudo apt-get install sbt
```

Then download the code and compile

```
git clone https://github.com/pnmougel/meow.git
cd meow
sbt run
```

## Features

* Easily create folders for the gnome menu

* Create new desktop entries by dragging an application or an url to the application
