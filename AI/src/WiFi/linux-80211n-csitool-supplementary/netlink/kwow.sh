for var in `seq 1 100`;do 
 echo $var

  sudo ./l2 aTtest$var 
  sshpass -p 1234 scp aTtest$var yhk@192.168.245.1:~/Desktop/wifidata/
done

