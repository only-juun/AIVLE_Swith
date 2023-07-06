

for i in `sec 1 100';do 
  echo $var 
   sshpass -p 1234 scp aTtest$var.txt yonghwan@192.168.245.1:~/
done
