//#include "mainwindow.h"
#include <QApplication>
#include <QLabel>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    //MainWindow w;
    //w.show();
    QLabel *qlabel = new QLabel("hello,world");
    qlabel->show();

    return a.exec();
}
