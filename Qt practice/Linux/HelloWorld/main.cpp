#include <QApplication>
#include <QLabel>
/*
 * Hello,world
 *1. QLabel qlabel("hello,world");qlabel.show();
 * QLabel *qlabel = new QLabel("hello,world);qlabel->show();
 *2. qt
 *
 */
int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    QLabel qlabel("hello,world");
    qlabel.show();
    return a.exec();
}
