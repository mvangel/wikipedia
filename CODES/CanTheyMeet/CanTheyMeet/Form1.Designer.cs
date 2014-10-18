namespace CanTheyMeet
{
    partial class CanTheyMeet
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(CanTheyMeet));
            this.comboboxPerson2 = new System.Windows.Forms.ComboBox();
            this.comboboxPerson1 = new System.Windows.Forms.ComboBox();
            this.LabelPerson2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.button1 = new System.Windows.Forms.Button();
            this.result = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // comboboxPerson2
            // 
            this.comboboxPerson2.FormattingEnabled = true;
            this.comboboxPerson2.Location = new System.Drawing.Point(372, 41);
            this.comboboxPerson2.Name = "comboboxPerson2";
            this.comboboxPerson2.Size = new System.Drawing.Size(300, 21);
            this.comboboxPerson2.TabIndex = 0;
            this.comboboxPerson2.KeyUp += new System.Windows.Forms.KeyEventHandler(this.comboboxPerson2_KeyUp);
            // 
            // comboboxPerson1
            // 
            this.comboboxPerson1.FormattingEnabled = true;
            this.comboboxPerson1.Location = new System.Drawing.Point(12, 41);
            this.comboboxPerson1.Name = "comboboxPerson1";
            this.comboboxPerson1.Size = new System.Drawing.Size(300, 21);
            this.comboboxPerson1.TabIndex = 1;
            this.comboboxPerson1.KeyUp += new System.Windows.Forms.KeyEventHandler(this.comboboxPerson1_KeyUp);
            // 
            // LabelPerson2
            // 
            this.LabelPerson2.AutoSize = true;
            this.LabelPerson2.Location = new System.Drawing.Point(371, 15);
            this.LabelPerson2.Name = "LabelPerson2";
            this.LabelPerson2.Size = new System.Drawing.Size(56, 13);
            this.LabelPerson2.TabIndex = 2;
            this.LabelPerson2.Text = "Person #2";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 15);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(56, 13);
            this.label1.TabIndex = 3;
            this.label1.Text = "Person #1";
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(298, 87);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(88, 45);
            this.button1.TabIndex = 4;
            this.button1.Text = "CanTheyMeet";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // result
            // 
            this.result.AutoSize = true;
            this.result.Font = new System.Drawing.Font("Impact", 48F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.result.Location = new System.Drawing.Point(284, 172);
            this.result.Name = "result";
            this.result.Size = new System.Drawing.Size(207, 80);
            this.result.TabIndex = 5;
            this.result.Text = "Result";
            // 
            // CanTheyMeet
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(684, 261);
            this.Controls.Add(this.result);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.LabelPerson2);
            this.Controls.Add(this.comboboxPerson1);
            this.Controls.Add(this.comboboxPerson2);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "CanTheyMeet";
            this.Text = "CanTheyMeet";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ComboBox comboboxPerson2;
        private System.Windows.Forms.ComboBox comboboxPerson1;
        private System.Windows.Forms.Label LabelPerson2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Label result;
    }
}

