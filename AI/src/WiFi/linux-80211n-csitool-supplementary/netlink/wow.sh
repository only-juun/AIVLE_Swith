for var in `seq 1 100`;do 
 echo $var

  sudo ./log_to_file2 ./Realtime_test/aTtest$var 
  sshpass -p 1234 scp aTtest$var yonghwan@192.168.245.1:~/
done


