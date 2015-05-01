//qt5
#include <QApplication>
#include <QPushButton>
/*
 * signalslot
 *
 */
int main(int argc,char *argv[])
{
    QApplication app(argc,argv);
    QPushButton button("Quit");
    QObject::connect(&button, &QPushButton::clicked, &QApplication::quit);
    button.show();

    return app.exec();
}
