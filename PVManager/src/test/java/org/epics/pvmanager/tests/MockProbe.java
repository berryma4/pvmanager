/**
 * Copyright (C) 2010-12 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */
/*
 * MockPVFrame.java
 *
 * Created on Feb 16, 2010, 3:43:37 PM
 */
package org.epics.pvmanager.tests;

import java.awt.Color;
import java.util.EnumMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import org.epics.pvmanager.*;
import org.epics.pvmanager.sim.SimulationDataSource;
import org.epics.pvmanager.data.*;
import org.epics.pvmanager.jca.JCADataSource;
import static org.epics.pvmanager.formula.ExpressionLanguage.*;
import static org.epics.pvmanager.ExpressionLanguage.*;
import static org.epics.pvmanager.util.Executors.*;
import static org.epics.util.time.TimeDuration.*;
import org.epics.util.time.TimeDuration;

/**
 *
 * @author carcassi
 */
public class MockProbe extends javax.swing.JFrame {

    Map<AlarmSeverity, Border> borders = new EnumMap<AlarmSeverity, Border>(AlarmSeverity.class);

    /**
     * Creates new form MockPVFrame
     */
    public MockProbe() {
        initComponents();
        borders.put(AlarmSeverity.NONE, pvTextValue.getBorder());
        borders.put(AlarmSeverity.MINOR, new LineBorder(Color.YELLOW));
        borders.put(AlarmSeverity.MAJOR, new LineBorder(Color.RED));
        borders.put(AlarmSeverity.INVALID, new LineBorder(Color.GRAY));
        borders.put(AlarmSeverity.UNDEFINED, new LineBorder(Color.MAGENTA));
    }
    ValueFormat format = new SimpleValueFormat(3);

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pvName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        pvTextValue = new javax.swing.JTextField();
        pvType = new javax.swing.JTextField();
        lastError = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pvTime = new javax.swing.JTextField();
        indicator = new javax.swing.JSlider();
        jLabel6 = new javax.swing.JLabel();
        metadata = new javax.swing.JTextField();
        channelDetailsButton = new javax.swing.JButton();
        connected = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        writeConnected = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Value:");

        jLabel2.setText("PV name:");

        pvName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pvNameActionPerformed(evt);
            }
        });

        jLabel3.setText("Last error:");

        jLabel5.setText("Type:");

        pvTextValue.setEditable(false);

        pvType.setEditable(false);

        lastError.setEditable(false);

        jLabel4.setText("Timestamp:");

        pvTime.setEditable(false);

        indicator.setEnabled(false);

        jLabel6.setText("Metadata:");

        metadata.setEditable(false);

        channelDetailsButton.setText("Channel details...");
        channelDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                channelDetailsButtonActionPerformed(evt);
            }
        });

        connected.setEditable(false);

        jLabel7.setText("Connected:");

        jLabel8.setText("Write Connected:");

        writeConnected.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pvName, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
                    .addComponent(indicator, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pvTextValue, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pvTime, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pvType, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lastError, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(metadata))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(connected))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(channelDetailsButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(writeConnected)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pvName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(indicator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(pvTextValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pvTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pvType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastError, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(metadata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(connected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(writeConnected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(channelDetailsButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pvNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pvNameActionPerformed
        if (pv != null) {
            pv.close();
            lastError.setText("");
        }

        try {
            pv = PVManager.readAndWrite(channel(pvName.getText()))
                    .timeout(TimeDuration.ofSeconds(5))
                    .readListener(new PVReaderListener() {
                            @Override
                            public void pvChanged(PVReader pvReader) {
                                setLastError(pv.lastException());
                                Object value = pv.getValue();
                                setTextValue(format.format(value));
                                setType(ValueUtil.typeOf(value));
                                setAlarm(ValueUtil.alarmOf(value));
                                setTime(ValueUtil.timeOf(value));
                                setIndicator(ValueUtil.normalizedNumericValueOf(value));
                                setMetadata(ValueUtil.displayOf(value));
                                setConnected(pv.isConnected());
                            }
                        })
                    .listeners(new PVWriterListener() {

                        @Override
                        public void pvWritten() {
                            setWriteConnected(pv.isWriteConnected());
                        }
                    })
                    .asynchWriteAndMaxReadRate(ofHertz(10));
        } catch (RuntimeException ex) {
            setLastError(ex);
        }

    }//GEN-LAST:event_pvNameActionPerformed

    private void channelDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_channelDetailsButtonActionPerformed
        try {
            ChannelHandler handler = PVManager.getDefaultDataSource().getChannels().get(pvName.getText());
            if (handler != null) {
                Map<String, Object> properties = handler.getProperties();
                StringBuilder builder = new StringBuilder();
                builder.append("Channel properties:\n");
                for (Map.Entry<String, Object> entry : properties.entrySet()) {
                    String string = entry.getKey();
                    Object object = entry.getValue();
                    builder.append(string).append(" = ").append(object).append("\n");
                }
                JOptionPane.showMessageDialog(this, builder.toString());
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_channelDetailsButtonActionPerformed
    PV<?,?> pv;

    private void setTextValue(String value) {
        if (value == null) {
            pvTextValue.setText("");
        } else {
            pvTextValue.setText(value);
        }
    }

    private void setType(Class type) {
        if (type == null) {
            pvType.setText("");
        } else {
            pvType.setText(type.getSimpleName());
        }
    }

    private void setAlarm(Alarm alarm) {
        if (alarm != null) {
            pvTextValue.setBorder(borders.get(alarm.getAlarmSeverity()));
        } else {
            pvTextValue.setBorder(borders.get(AlarmSeverity.NONE));
        }
    }

    private void setTime(Time time) {
        if (time == null) {
            pvTime.setText("");
        } else {
            pvTime.setText(time.getTimestamp().toDate().toString());
        }
    }

    private void setMetadata(Display display) {
        if (display == null) {
            metadata.setText("");
        } else {
            metadata.setText(display.getUpperDisplayLimit() + " - " + display.getLowerDisplayLimit());
        }
    }

    private void setLastError(Exception ex) {
        if (ex != null) {
            ex.printStackTrace();
            lastError.setText(ex.getClass().getSimpleName() + " " + ex.getMessage());
        } else {
        }
    }

    private void setConnected(Boolean connected) {
        if (connected != null) {
            this.connected.setText(connected.toString());
        } else {
            this.connected.setText("");
        }
    }

    private void setWriteConnected(Boolean connected) {
        if (connected != null) {
            this.writeConnected.setText(connected.toString());
        } else {
            this.writeConnected.setText("");
        }
    }

    private void setIndicator(Double value) {
        int range = indicator.getMaximum() - indicator.getMinimum();
        int position = range / 2;
        if (value != null) {
            position = (int) (range * value);
        }
        indicator.setValue(position);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        SetupUtil.defaultCASetupForSwing();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MockProbe().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton channelDetailsButton;
    private javax.swing.JTextField connected;
    private javax.swing.JSlider indicator;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField lastError;
    private javax.swing.JTextField metadata;
    private javax.swing.JTextField pvName;
    private javax.swing.JTextField pvTextValue;
    private javax.swing.JTextField pvTime;
    private javax.swing.JTextField pvType;
    private javax.swing.JTextField writeConnected;
    // End of variables declaration//GEN-END:variables
}
