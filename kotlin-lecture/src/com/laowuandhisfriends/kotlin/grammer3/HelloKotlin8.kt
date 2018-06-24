package com.laowuandhisfriends.kotlin.grammer3

import java.awt.event.ActionListener
import java.awt.event.WindowEvent
import java.awt.event.WindowListener
import javax.swing.JButton
import javax.swing.JFrame

fun main(args: Array<String>) {
    val jFrame = JFrame("My JFrame")
    val jButton = JButton("My JButton")

    jFrame.addWindowListener(object : WindowListener {
        override fun windowDeiconified(e: WindowEvent?) {
        }

        override fun windowClosing(e: WindowEvent?) {
            println("windowClosing")
        }

        override fun windowClosed(e: WindowEvent?) {
        }

        override fun windowActivated(e: WindowEvent?) {
            println("windowActivated")
        }

        override fun windowDeactivated(e: WindowEvent?) {
        }

        override fun windowOpened(e: WindowEvent?) {
            println("windowOpened")
        }

        override fun windowIconified(e: WindowEvent?) {
        }
    })

    jButton.addActionListener({ println("actionPerformed") })

    val listener = ActionListener { println("hello world") }
    println(listener.javaClass)
    println(listener.javaClass.superclass)
    println(listener::class.java)
    println(listener::class.java.superclass)

    jFrame.add(jButton)
    jFrame.pack()
    jFrame.isVisible = true
    jFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
}