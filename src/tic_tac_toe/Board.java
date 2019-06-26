/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author NITESH
 */

public class Board extends javax.swing.JFrame {

    BufferedImage img1=null,img2=null;
        ImageIcon i1,i2;
    int hc=0;//0 for comp ; 1 for human.
    int c=0;
    int d[]=new int[9];
    int x[]=new int[9];
    /**
     * Creates new form Board
     */
    public Board() 
    {
        try
	{
            img1=ImageIO.read(new File("src/tic_tac_toe/circled.png"));
            img2=ImageIO.read(new File("src/tic_tac_toe/clipart-0119.png"));
        }
	catch(Exception e)
	{
            e.printStackTrace();
	}
	Image dimg1=img1.getScaledInstance(70,64,Image.SCALE_SMOOTH);
        Image dimg2=img2.getScaledInstance(70,64,Image.SCALE_SMOOTH);
	i1=new ImageIcon(dimg1);
        i2=new ImageIcon(dimg2);
        this.hc=Welcome.hc;
        System.out.println(this.hc);
        initComponents();
        
    }
    public int full(int x[])
    {
        for(int i=0;i<9;i++)
        {
            if(x[i]==0)
                return -1;
        }
        return 1;
    }
    public void check()
    {
        if(value(this.d)==1){
            JOptionPane.showMessageDialog(this,"X WON!!");
            this.dispose();
            Board br=new Board();
            br.setVisible(true);
        }
        else
            if(value(this.d)==-1)
            {
                JOptionPane.showMessageDialog(this,"O WON!!");
                this.dispose();
                Board br=new Board();
                br.setVisible(true);      
            }
        else
                if(full(this.d)==1)
                {
                    
                JOptionPane.showMessageDialog(null,"DRAW");
                this.dispose();
                Board br=new Board();
                br.setVisible(true);
            
                }
    }
    public int value(int d[])
    {
        //d[i]==1 is for X.
        //d[i]==2 is for O.
        if(d[0]==d[1]&&d[1]==d[2])
        {
            if(d[0]==1)
                return 1;
            if(d[0]==2)
                return -1;
            else
                return 0;
        }
        if(d[3]==d[4]&&d[4]==d[5])
        {
            if(d[3]==1)
                return 1;
            if(d[3]==2)
                return -1;
            else
                return 0;
        }
        if(d[6]==d[7]&&d[7]==d[8])
        {
            if(d[6]==1)
                return 1;
            if(d[6]==2)
                return -1;
            else
                return 0;
        }
        if(d[0]==d[3]&&d[3]==d[6])
        {
            if(d[0]==1)
                return 1;
            if(d[0]==2)
                return -1;
            else
                return 0;
        }
        if(d[1]==d[4]&&d[4]==d[7])
        {
            if(d[1]==1)
                return 1;
            if(d[1]==2)
                return -1;
            else
                return 0;
        }
        if(d[2]==d[5]&&d[5]==d[8])
        {
            if(d[2]==1)
                return 1;
            if(d[2]==2)
                return -1;
            else
                return 0;
        }
        if(d[0]==d[4]&&d[4]==d[8])
        {
            if(d[0]==1)
                return 1;
            if(d[0]==2)
                return -1;
            else
                return 0;
        }
        if(d[2]==d[4]&&d[4]==d[6])
        {
            if(d[2]==1)
                return 1;
            if(d[2]==2)
                return -1;
            else
                return 0;
        }
        return 0;
    }
    
    public int max(int a,int b)
    {
        if(a>b)
            return a;
        else
            return b;
    }
    public int min(int a,int b)
    {
        if(a<b)
            return a;
        else
            return b;
    }
    
    public int nextMove()
    {
        int j=0;
        int v=-10;
        int t;
        
        for(int i=0;i<9;i++)
        {
            this.x[i]=this.d[i];
        }
        for(int i=0;i<9;i++)//HERE WE R INITIALISING MINMAX FOR ALL EMPTY SQUARES.|
        {                                                                      // |
            if(this.x[i]==0)                                                  //  |
            {                                                                //   |
                this.x[i]=1;                                                //  <<=
                //System.out.println("NEXTMOVE i="+i);
                t=this.minMax(i,1);
                if(t>v)
                {
                   v=t;
                   j=i;
                }
                this.x[i]=0;
            }
        }
        //System.out.println("                          Returning j="+j);
        return j;
    }
    public int minMax(int i,int type)
    {   
        
        int v=value(this.x);
        if(v==-1||v==1)
        {
            this.x[i]=0;
            //System.out.println("RETURNING FOR V="+v);
            return v;
        }
        if(full(this.x)==1)
        {
            this.x[i]=0;
            //System.out.println("FULL");
            return 0;
        }
            
        if(type==1)
        {
            //System.out.println("TYPE-O");
            //FOR TYPE-O , -1 MEANS WIN.
            int vl=10;
            //SINCE WE NEED MINIMUM FOR 'O' WHICH IS ITS BEST CASE,WE START WITH -VE INFINITY.
            //HERE VALUE OF MIN-MAX RANGES FROM (-1,1).
            for(int j=0;j<9;j++)
            {
                if(this.x[j]==0)
                {
                    this.x[j]=2;
                    //System.out.println("MINMAX j="+j);
                    vl=this.min(vl,minMax(j,2));//SINCE '-1' IS WIN FOR 'O' , IT GOES FOR MINIMUM VALUE OF MINMAX().
                    this.x[j]=0;
                }
            }
            //this.x[i]=0;
            return vl;
        }
        else
        {
            //System.out.println("TYPE-X");
            //FOR TYPE-X , 1 MEANS WIN.
            int vl=-10;
            //SINCE WE NEED MAXIMUM FOR 'X' WHICH IS ITS BEST CASE,WE START WITH -VE INFINITY.
            //HERE VALUE OF MIN-MAX RANGES FROM (-1,1). 
            for(int j=0;j<9;j++)
            {
                if(this.x[j]==0)
                {
                    this.x[j]=1;
                    //System.out.println("MINMAX j="+j);
                    vl=this.max(vl,minMax(j,1));//SINCE '1' IS WIN FOR 'X' , IT GOES FOR MAXIMUM VALUE OF MINMAX().
                    this.x[j]=0;
                }
            }
            //this.x[i]=0;
            return vl;
        }
    }
    
    public void set(int s)
    {
        if(s==0)
            jb1.setIcon(i2);
        if(s==1)
            jb2.setIcon(i2);
        if(s==2)
            jb3.setIcon(i2);
        if(s==3)
            jb4.setIcon(i2);
        if(s==4)
            jb5.setIcon(i2);
        if(s==5)
            jb6.setIcon(i2);
        if(s==6)
            jb7.setIcon(i2);
        if(s==7)
            jb8.setIcon(i2);
        if(s==8)
            jb9.setIcon(i2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Bg = new javax.swing.JLabel();
        jb1 = new javax.swing.JButton();
        jb2 = new javax.swing.JButton();
        jb3 = new javax.swing.JButton();
        jb4 = new javax.swing.JButton();
        jb5 = new javax.swing.JButton();
        jb6 = new javax.swing.JButton();
        jb7 = new javax.swing.JButton();
        jb8 = new javax.swing.JButton();
        jb9 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tic-Tac-Toe");
        addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                formAncestorResized(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 153), 1, true), javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 255, 255), new java.awt.Color(51, 153, 255))));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb1ActionPerformed(evt);
            }
        });

        jb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb2ActionPerformed(evt);
            }
        });

        jb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb3ActionPerformed(evt);
            }
        });

        jb4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb4ActionPerformed(evt);
            }
        });

        jb5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb5ActionPerformed(evt);
            }
        });

        jb6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb6ActionPerformed(evt);
            }
        });

        jb7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb7ActionPerformed(evt);
            }
        });

        jb8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb8ActionPerformed(evt);
            }
        });

        jb9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb9ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Maiandra GD", 3, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TIC-TAC-TOE");

        jButton1.setText("RESTART");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Go to Main Menu");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(264, 264, 264)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jb9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jb6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(Bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(53, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jb4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jb7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jb1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jb2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jb5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jb8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jb3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(Bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jb2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jb3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jb1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jb4, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                            .addComponent(jb6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jb5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jb9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jb8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jb7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1))
                        .addGap(28, 28, 28))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jb5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb5ActionPerformed
    if(this.hc==0)
    {
        if(this.d[4]==1||this.d[4]==2)
            JOptionPane.showMessageDialog(null,"ILLEGAL ATTEMPT");
        else
        if(this.c==0)
        {
            jb5.setIcon(i1);
            
            this.d[4]=2;
            check();
            if(full(this.d)==1)
            {
                JOptionPane.showMessageDialog(null,"DRAW"); 
                this.dispose();
                Board br=new Board();
                br.setVisible(true);
            }
            else
            {
                int s=nextMove();
                this.set(s);
                this.d[s]=1;
            }
        }
       check();
    }
    else
    {
        if(this.d[4]==1||this.d[4]==2)
            JOptionPane.showMessageDialog(null,"ILLEGAL ATTEMPT");
        else
        if(this.c==0)
        {
            jb5.setIcon(i1);
            this.d[4]=2;
            this.c=1;
        }
        else
        {
            jb5.setIcon(i2);
            this.d[4]=1;
            this.c=0;
        }
        check();
    }
    }//GEN-LAST:event_jb5ActionPerformed

    private void jb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb1ActionPerformed
    if(this.hc==0)
    {
        if(this.d[0]==1||this.d[0]==2)
            JOptionPane.showMessageDialog(null,"ILLEGAL ATTEMPT");
        else
        if(this.c==0)
        {
            //jb1.setIcon(i1);
            jb1.setIcon(i1);
            this.d[0]=2;
            if(full(this.d)==1)
                {                JOptionPane.showMessageDialog(null,"DRAW");                this.dispose();                Board br=new Board();                br.setVisible(true);            }
            else
            {
                int s=nextMove();
                this.set(s);
                this.d[s]=1;
            }
        }
        check();
    }
    else
    {
        if(this.d[0]==1||this.d[0]==2)
            JOptionPane.showMessageDialog(null,"ILLEGAL ATTEMPT");
        else
        if(this.c==0)
        {
            jb1.setIcon(i1);
            this.d[0]=2;
            this.c=1;
        }
        else
        {
            jb1.setIcon(i2);
            this.d[0]=1;
            this.c=0;
        }
        check();
    }
    }//GEN-LAST:event_jb1ActionPerformed

    private void jb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb2ActionPerformed
    if(this.hc==0)
    {
        if(this.d[1]==1||this.d[1]==2)
            JOptionPane.showMessageDialog(null,"ILLEGAL ATTEMPT");
        else
        if(this.c==0)
        {
            jb2.setIcon(i1);
            this.d[1]=2;
            if(full(this.d)==1)
                {                JOptionPane.showMessageDialog(null,"DRAW");                this.dispose();                Board br=new Board();                br.setVisible(true);            }
            else
            {
                int s=nextMove();
                this.set(s);
                this.d[s]=1;
            }
        }
        check();
    }
    else
    {
        if(this.d[1]==1||this.d[1]==2)
            JOptionPane.showMessageDialog(null,"ILLEGAL ATTEMPT");
        else
        if(this.c==0)
        {
            jb2.setIcon(i1);
            this.d[1]=2;
            this.c=1;
        }
        else
        {
            jb2.setIcon(i2);
            this.d[1]=1;
            this.c=0;
        }
        check();
    }
    }//GEN-LAST:event_jb2ActionPerformed

    private void jb4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb4ActionPerformed
    if(this.hc==0)
    {
        if(this.d[3]==1||this.d[3]==2)
            JOptionPane.showMessageDialog(null,"ILLEGAL ATTEMPT");
        else
        if(this.c==0)
        {
            jb4.setIcon(i1);
            this.d[3]=2;
            if(full(this.d)==1)
                {                JOptionPane.showMessageDialog(null,"DRAW");                this.dispose();                Board br=new Board();                br.setVisible(true);            }
            else
            {
                int s=nextMove();
                this.set(s);
                this.d[s]=1;
            }
        }
        check();
    }
    else
    {
        if(this.d[3]==1||this.d[3]==2)
            JOptionPane.showMessageDialog(null,"ILLEGAL ATTEMPT");
        else
        if(this.c==0)
        {
            jb4.setIcon(i1);
            this.d[3]=2;
            this.c=1;
        }
        else
        {
            jb4.setIcon(i2);
            this.d[3]=1;
            this.c=0;
        }
        check();
    }
    }//GEN-LAST:event_jb4ActionPerformed

    private void jb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb3ActionPerformed
    if(this.hc==0)
    {    
        if(this.d[2]==1||this.d[2]==2)
            JOptionPane.showMessageDialog(null,"ILLEGAL ATTEMPT");
        else
        if(this.c==0)
        {
            jb3.setIcon(i1);
            this.d[2]=2;
            if(full(this.d)==1)
                {                JOptionPane.showMessageDialog(null,"DRAW");                this.dispose();                Board br=new Board();                br.setVisible(true);            }
            else
            {
                int s=nextMove();
                this.set(s);
                this.d[s]=1;
            }
        }
        check();
    }
    else
    {
        if(this.d[2]==1||this.d[2]==2)
            JOptionPane.showMessageDialog(null,"ILLEGAL ATTEMPT");
        else
        if(this.c==0)
        {
            jb3.setIcon(i1);
            this.d[2]=2;
            this.c=1;
        }
        else
        {
            jb3.setIcon(i2);
            this.d[2]=1;
            this.c=0;
        }
        check();
    }
    }//GEN-LAST:event_jb3ActionPerformed

    private void jb6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb6ActionPerformed
    if(this.hc==0)
    {    
        if(this.d[5]==1||this.d[5]==2)
            JOptionPane.showMessageDialog(null,"ILLEGAL ATTEMPT");
        else
        if(this.c==0)
        {
            jb6.setIcon(i1);
            this.d[5]=2;
            if(full(this.d)==1)
                {                JOptionPane.showMessageDialog(null,"DRAW");                this.dispose();                Board br=new Board();                br.setVisible(true);            }
            else
            {
                int s=nextMove();
                this.set(s);
                this.d[s]=1;
            }
        }
        check();
    }
    else
    {
        if(this.d[5]==1||this.d[5]==2)
            JOptionPane.showMessageDialog(null,"ILLEGAL ATTEMPT");
        else
        if(this.c==0)
        {
            jb6.setIcon(i1);
            this.d[5]=2;
            this.c=1;
        }
        else
        {
            jb6.setIcon(i2);
            this.d[5]=1;
            this.c=0;
        }
        check();
    }
    }//GEN-LAST:event_jb6ActionPerformed

    private void jb7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb7ActionPerformed
    if(this.hc==0)
    {    
        if(this.d[6]==1||this.d[6]==2)
            JOptionPane.showMessageDialog(null,"ILLEGAL ATTEMPT");
        else
        if(this.c==0)
        {
            jb7.setIcon(i1);
            this.d[6]=2;
            if(full(this.d)==1)
                {                JOptionPane.showMessageDialog(null,"DRAW");                this.dispose();                Board br=new Board();                br.setVisible(true);            }
            else
            {
                int s=nextMove();
                this.set(s);
                this.d[s]=1;
            }
        }
        check();
    }
    else
    {
        if(this.d[6]==1||this.d[6]==2)
            JOptionPane.showMessageDialog(null,"ILLEGAL ATTEMPT");
        else
        if(this.c==0)
        {
            jb7.setIcon(i1);
            this.d[6]=2;
            this.c=1;
        }
        else
        {
            jb7.setIcon(i2);
            this.d[6]=1;
            this.c=0;
        }
        check();
    }
    }//GEN-LAST:event_jb7ActionPerformed

    private void jb8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb8ActionPerformed
    if(this.hc==0)
    {    
        if(this.d[7]==1||this.d[7]==2)
            JOptionPane.showMessageDialog(null,"ILLEGAL ATTEMPT");
        else
        if(this.c==0)
        {
            jb8.setIcon(i1);
            this.d[7]=2;
            if(full(this.d)==1)
                {                JOptionPane.showMessageDialog(null,"DRAW");                this.dispose();                Board br=new Board();                br.setVisible(true);            }
            else
            {
                int s=nextMove();
                this.set(s);
                this.d[s]=1;
            }
        }
        check();
    }
    else
    {
        if(this.d[7]==1||this.d[7]==2)
            JOptionPane.showMessageDialog(null,"ILLEGAL ATTEMPT");
        else
        if(this.c==0)
        {
            jb8.setIcon(i1);
            this.d[7]=2;
            this.c=1;
        }
        else
        {
            jb8.setIcon(i2);
            this.d[7]=1;
            this.c=0;
        }
        check();
    }
    }//GEN-LAST:event_jb8ActionPerformed

    private void jb9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb9ActionPerformed
    if(this.hc==0)
    {    
        if(this.d[8]==1||this.d[8]==2)
            JOptionPane.showMessageDialog(null,"ILLEGAL ATTEMPT");
        else
        if(this.c==0)
        {
            jb9.setIcon(i1);
            this.d[8]=2;
            if(full(this.d)==1)
                {                JOptionPane.showMessageDialog(null,"DRAW");                this.dispose();                Board br=new Board();                br.setVisible(true);            }
            else
            {
                int s=nextMove();
                this.set(s);
                this.d[s]=1;
            }
        }
        check();
    }
    else
    {
        if(this.d[8]==1||this.d[8]==2)
            JOptionPane.showMessageDialog(null,"ILLEGAL ATTEMPT");
        else
        if(this.c==0)
        {
            jb9.setIcon(i1);
            this.d[8]=2;
            this.c=1;
        }
        else
        {
            jb9.setIcon(i2);
            this.d[8]=1;
            this.c=0;
        }
        check();
    }
    }//GEN-LAST:event_jb9ActionPerformed

    private void formAncestorResized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formAncestorResized
        // TODO add your handling code here:
    }//GEN-LAST:event_formAncestorResized

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        Board bd=new Board();
        bd.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        Welcome w=new Welcome();
        w.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed
    
                                       
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Board().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Bg;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jb1;
    private javax.swing.JButton jb2;
    private javax.swing.JButton jb3;
    private javax.swing.JButton jb4;
    private javax.swing.JButton jb5;
    private javax.swing.JButton jb6;
    private javax.swing.JButton jb7;
    private javax.swing.JButton jb8;
    private javax.swing.JButton jb9;
    // End of variables declaration//GEN-END:variables
}
