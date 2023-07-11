for var in `seq 3 50`;do
 echo $var

  sshpass -p 1234 scp ./Realtimetxt/aTtest$var.txt yonghwan@192.168.245.1:~/$var
  sleep 3
done

