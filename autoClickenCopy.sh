#! /bin/bash

counter=1
while [ $counter -le 3 ]
do
	sshpass -f <(printf '%s\n' sniper) ssh airteam@192.168.1.22 gphoto2 --capture-image-and-download --filename /home/airteam/Desktop/Image${counter}.jpg
	sshpass -f <(printf '%s\n' sniper) scp airteam@192.168.1.22:/home/airteam/Desktop/Image${counter}.jpg ./Img${counter}.jpg
	((counter++))
done

echo All done
