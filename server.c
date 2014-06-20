
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <string>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <time.h>
#include <fstream>
#include <iostream>
#include <thread>


#define PORT        1988
#define MAX_CLIENTS 5

#define STREAM      1
#define DOOR        2
#define OK          3
#define ERROR       4
#define DOORMIN     20000000
#define QUIT        9
#define OPEN_DOOR_CMD "/home/pi/openDoor"


using namespace std;

//Global logger
streambuf * backup, * psbuf;
ofstream filestr;
int u_ID  = 0;
int dl_ID = 0;
int doorValues = 0;


void openDoor()
{
    
    if(doorValues == 0)
        return;
    //get three last digits for user id
    u_ID = doorValues % 100;
    
    cout << "doorValues : " << doorValues << "\n" << flush;
    //convert user is to zeros
    int temp = doorValues - u_ID;
    temp = temp / 1000;
    dl_ID = temp % 10000;
    
    cout << "u_id  : " << u_ID << "\n" << flush;
    cout << "dl_id : " << dl_ID << "\n" << flush;
    
    
    string sCommand = OPEN_DOOR_CMD;
    sCommand.append(" ");
    sCommand.append(to_string(dl_ID));
    sCommand.append(" ");
    sCommand.append(to_string(u_ID));
    
    
    //system(sCommand);
    
    u_ID  = 0;
    dl_ID = 0;
}


void startThread()
{
    thread tDoor(openDoor);
    tDoor.detach();
}


/*
 *Function closes the opened log file and redirects clog to default
 *
 */
void closeLog()
{
	clog << "closing logger" << endl;
    
	//set clog back to default
	if(backup != NULL)
		clog.rdbuf(backup);
        
    filestr.close();
}


/*
 *Function opens a log file and redirects clog to the file for output
 *
 */
void openLog(string sName)
{
    //backup the clog system default
	backup = clog.rdbuf();
    
    //get the current directory
    char dirPath[256];
    getcwd(dirPath, 255);
    
    string path = dirPath;
    
    //cout << "current dir: " << dirPath << "\n" << flush;
    path.append("/");
    path.append(sName);
    path.append(".txt");
    
    //cout << "file path: " << path << "\n" << flush;
    
    filestr.open(path.c_str(), ios::out | ios::app);
    
    if (!filestr.is_open())
        cout << "couldn't create log file\n" << flush;
    else
    {
        //redirect clog to opened file
        psbuf = filestr.rdbuf();
        clog.rdbuf(psbuf);
        
        clog << endl << endl << flush;
        clog << "++++++++++++++++++++++++++++++++++++++++++++\n" << flush;
        clog << "++++++++++++++++++++++++++++++++++++++++++++\n" << flush;
        clog << " " << sName << "\n"                             << flush;
        clog << " Logger for current socket server session\n"    << flush;
        clog << " using port " << PORT << "\n"                   << flush;
        clog << "++++++++++++++++++++++++++++++++++++++++++++\n" << flush;
        clog << "++++++++++++++++++++++++++++++++++++++++++++\n" << flush;
        
    }
}


/*
 *Function to send data to the client
 *
 */
void sendData( int sockfd, int x ) {
    int n;
    
    char buffer[10];
    sprintf( buffer, "%d\n", x );
    if ( (n = write( sockfd, buffer, strlen(buffer) ) ) < 0 )
        clog << "ERROR writing to socket \n" << flush;
    buffer[n] = '\0';
}

/*
 *Function to read chars sent by the client
 *
 */
int getData( int sockfd ) {
    char buffer[10];
    int n;
    
    if ( (n = read(sockfd,buffer,9) ) < 0 )
        clog << "ERROR reading from socket \n" << flush;
    buffer[n] = '\0';
    return atoi(buffer);
}




int main(int argc, char *argv[]) {
    
    int sockfd;
    int newsockfd;
    int clilen;
    int n;
    int iData;
    
    struct sockaddr_in serv_addr;
    struct sockaddr_in cli_addr;
    
    //get current time
    time_t _tm =time(NULL);
    
    struct tm * curtime = localtime (&_tm);
    string sTime = asctime(curtime);

    //create a logger
    openLog(sTime);

    
    //create socket
    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    
    if (sockfd < 0)
        clog << "ERROR opening socket\n" << flush;
    
    //fill with zeros
    bzero((char *) &serv_addr, sizeof(serv_addr));
    
    //configure socket using TCP
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = INADDR_ANY;
    serv_addr.sin_port = htons( PORT );
    
    if (bind(sockfd, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0)
        clog << "ERROR on binding\n" << flush;
    
    //listen to MAX_CLIENTS clients
    listen(sockfd, MAX_CLIENTS);
    clilen = sizeof(cli_addr);
    
    //--- infinite wait on a connection ---
    while ( true )
    {
        if ( (newsockfd = accept(sockfd, (struct sockaddr *) &cli_addr,
                (socklen_t*) &clilen) ) < 0 )
            clog << "ERROR on accept\n" << flush;
        
        clog << "opened new communication with client\n" << flush;
        
        
        while ( true )
        {
            
            //---- wait for a request from client ---
            iData = getData( newsockfd );
            //clog << "\n\ngetData: " << iData << "\n" << flush;
            
            if (iData != 0)
            {
                int info = 0;
                
                if (iData > DOORMIN)
                    info = 2;
                else
                    info = iData;
                    
                switch (info)
                {
                    
                    //open stream
                    case STREAM:
                        clog << "got STREAM\n" << flush;
                        //system("startStream.sh");
                        sendData(newsockfd, OK);
                        break;
                    
                    //open door
                    //case DOOR:
                    case 2:
                        clog << "got DOOR\n" << flush;
                        doorValues = iData;
                        startThread();
                        sendData(newsockfd, OK);
                        break;
                        
                    //error
                    default:
                       cout << "ERROR client sent unknown request: "
                            << iData << "\n" << flush;
                        sendData(newsockfd, ERROR);
                        break;
                }
                
            }
            else
            {
                clog << "Server received zero.....\n" << flush;
                break;
            }
        }//second while
        
        
//        if(iData == QUIT)
//        {
//            sendData(newsockfd, QUIT);
//            clog << "wrote " << QUIT << "\n" << flush;
//            close( newsockfd );
//            clog << "socket closed\n" << flush;
//            break;
//        }
        
    }//first while
    
    closeLog();

    clog << "Transmition terminated by client\n" << flush;
    clog << "-- E N D --\n" << flush;
    return 0; 
}
