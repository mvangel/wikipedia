using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WikiParser
{
    class Program
    {
        static void Main(string[] args)
        {
            string line = "";
            string line2 = "";
            string line3 = "";
            System.IO.StreamReader file = new System.IO.StreamReader("c:\\work\\VI\\wikipedia\\rest9");
            System.IO.StreamWriter good = new System.IO.StreamWriter("c:\\work\\VI\\wikipedia\\finished10");
            System.IO.StreamWriter bad = new System.IO.StreamWriter("c:\\work\\VI\\wikipedia\\rest10");
            line=file.ReadLine();
            while (line != null)
            {
                string name = line;
                int Dmonth,Bmonth=-1;
                int Dday,Bday=-1;
                string birth = file.ReadLine();
                string death = file.ReadLine();
                #region CRICA REMOVE
                //try
                //{
                //    death = death.Split('=')[1];
                //    birth = birth.Split('=')[1];
                //    if (birth.Equals(""))
                //    {
                //        birth = "BIRTH=NOTSET";
                //    }
                //    else
                //    {
                //        if (birth.Split(' ').Count() == 2)
                //        {
                //            if (birth.Split(' ')[0].Equals("CIRCA"))
                //            {
                //                birth = "BIRTH=" + birth.Split(' ')[1] + "|0|0";
                //            }
                //            else birth = "BIRTH=" + birth;
                //        }
                //        else birth = "BIRTH=" + birth;
                //    }
                //    if (death.Equals(""))
                //    {
                //        death = "DEATH=NOTSET";
                //    }
                //    else
                //    {
                //        if (death.Split(' ').Count() == 3)
                //        {
                //            if (death.Split(' ')[0].Equals("CIRCA"))
                //            {
                //                death = "DEATH=" + death.Split(' ')[1] + "|0|0";
                //            }
                //            else death = "DEATH=" + death;
                //        }
                //        else death = "DEATH=" + death;
                //    }
                //    if ((birth.Contains('|') || birth.Equals("BIRTH=NOTSET")) && (death.Contains('|') || death.Equals("DEATH=NOTSET")))
                //    {
                //        good.WriteLine(name);
                //        good.WriteLine(birth);
                //        good.WriteLine(death);
                //    }
                //    else
                //    {
                //        bad.WriteLine(name);
                //        bad.WriteLine(birth);
                //        bad.WriteLine(death);
                //    }
                //}
                //catch (Exception e)
                //{
                //    try
                //    {
                //        string pom = death.Split('=')[1];
                //        if (pom.Equals(""))
                //        {
                //            death = "DEATH=NOTSET";
                //        }
                //    }
                //    catch (Exception ex)
                //    {
                //        death = "DEATH=NOTSET";
                //    }
                //    try
                //    {
                //        var pom = birth.Split('=')[1];
                //        if (pom.Equals(""))
                //        {
                //            birth = "BIRTH=NOTSET";
                //        }
                //    }
                //    catch (Exception exe)
                //    {
                //        birth = "BIRTH=NOTSET";
                //    }
                //    good.WriteLine(name);
                //    good.WriteLine(birth);
                //    good.WriteLine(death);
                //}
                #endregion
                #region Pre_datum_Mesiac_Den_rok_aleb_Den_mesiac_rok
                //try
                //{
                //    death = death.Split('=')[1];
                //    birth = birth.Split('=')[1];
                //    if (birth.Equals(""))
                //    {
                //        birth = "BIRTH=NOTSET";
                //    }
                //    else
                //    {
                //        if (birth.Split(' ').Count() == 3)
                //        {
                //            if ((StringToMonth(birth.Split(' ')[1]) != -1) || (StringToMonth(birth.Split(' ')[0]) != -1))
                //            {
                //                if ((StringToMonth(birth.Split(' ')[1]) != -1))
                //                {
                //                    Bmonth = StringToMonth(birth.Split(' ')[1]);
                //                    if (int.TryParse(birth.Split(' ')[0], out Bday))
                //                        birth = "BIRTH=" + birth.Split(' ')[2] + "|" + Bmonth + "|" + Bday;
                //                    else
                //                        birth = "BIRTH=" + birth.Split(' ')[2] + "|" + Bmonth + "|0";
                //                }
                //                else
                //                {
                //                    Bmonth = StringToMonth(birth.Split(' ')[0]);
                //                    if (int.TryParse(birth.Split(' ')[1], out Bday))
                //                        birth = "BIRTH=" + birth.Split(' ')[2] + "|" + Bmonth + "|" + Bday;
                //                    else
                //                        birth = "BIRTH=" + birth.Split(' ')[2] + "|" + Bmonth + "|0";
                //                }
                //            }
                //            else birth = "BIRTH=" + birth;
                //        }
                //        else birth = "BIRTH=" + birth;
                //    }
                //    if (death.Equals(""))
                //    {
                //        death = "DEATH=NOTSET";
                //    }
                //    else
                //    {
                //        if (death.Split(' ').Count() == 3)
                //        {
                //            if ((StringToMonth(death.Split(' ')[0]) != -1) || (StringToMonth(death.Split(' ')[1]) != -1))
                //            {
                //                if ((StringToMonth(death.Split(' ')[1]) != -1))
                //                {
                //                    Dmonth = StringToMonth(death.Split(' ')[1]);
                //                    if (int.TryParse(death.Split(' ')[0], out Dday))
                //                        death = "DEATH=" + death.Split(' ')[2] + "|" + Dmonth + "|" + Dday;
                //                    else
                //                        death = "DEATH=" + death.Split(' ')[2] + "|" + Dmonth + "|0";
                //                }
                //                else
                //                {
                //                    Dmonth = StringToMonth(death.Split(' ')[0]);
                //                    if (int.TryParse(death.Split(' ')[1], out Dday))
                //                        death = "DEATH=" + death.Split(' ')[2] + "|" + Dmonth + "|" + Dday;
                //                    else
                //                        death = "DEATH=" + death.Split(' ')[2] + "|" + Dmonth + "|0";
                //                }
                //            }
                //            else death = "DEATH=" + death;
                //        }
                //        else death = "DEATH=" + death;
                //    }
                //    if ((birth.Contains('|') || birth.Equals("BIRTH=NOTSET")) && (death.Contains('|') || death.Equals("DEATH=NOTSET")))
                //    {
                //        good.WriteLine(name);
                //        good.WriteLine(birth);
                //        good.WriteLine(death);
                //    }
                //    else
                //    {
                //        bad.WriteLine(name);
                //        bad.WriteLine(birth);
                //        bad.WriteLine(death);
                //    }
                //}
                //catch (Exception e)
                //{
                //    try
                //    {
                //        string pom = death.Split('=')[1];
                //        if (pom.Equals(""))
                //        {
                //            death = "DEATH=NOTSET";
                //        }
                //    }
                //    catch (Exception ex)
                //    {
                //        death = "DEATH=NOTSET";
                //    }
                //    try
                //    {
                //        var pom = birth.Split('=')[1];
                //        if (pom.Equals(""))
                //        {
                //            birth = "BIRTH=NOTSET";
                //        }
                //    }
                //    catch (Exception exe)
                //    {
                //        birth = "BIRTH=NOTSET";
                //    }
                //    good.WriteLine(name);
                //    good.WriteLine(birth);
                //    good.WriteLine(death);
                //}
                #endregion
                #region Pre_datum_YYYY-MM-DD
                //try
                //{
                //    death = death.Split('=')[1];
                //    birth = birth.Split('=')[1];
                //    if (birth.Equals(""))
                //    {
                //        birth = "BIRTH=NOTSET";
                //    }
                //    else
                //    {
                //        if (birth.Split('-').Count() == 3)
                //        {
                //            birth = "BIRTH=" + birth.Replace('-', '|');
                //        }
                //        else birth = "BIRTH=" + birth;
                //    }
                //    if (death.Equals(""))
                //    {
                //        death = "DEATH=NOTSET";
                //    }
                //    else
                //    {
                //        if (death.Split('-').Count() == 3)
                //        {
                //            death = "DEATH=" + death.Replace('-', '|');
                //        }
                //        else death = "DEATH=" + death;
                //    }
                //    if ((birth.Contains('|') || birth.Equals("BIRTH=NOTSET")) && (death.Contains('|') || death.Equals("DEATH=NOTSET")))
                //    {
                //        good.WriteLine(name);
                //        good.WriteLine(birth);
                //        good.WriteLine(death);
                //    }
                //    else
                //    {
                //        bad.WriteLine(name);
                //        bad.WriteLine(birth);
                //        bad.WriteLine(death);
                //    }
                //}

                //catch (Exception e)
                //{
                //    try
                //    {
                //        string pom = death.Split('=')[1];
                //        if (pom.Equals(""))
                //        {
                //            death = "DEATH=NOTSET";
                //        }
                //    }
                //    catch (Exception ex)
                //    {
                //        death = "DEATH=NOTSET";
                //    }
                //    try
                //    {
                //        var pom = birth.Split('=')[1];
                //        if (pom.Equals(""))
                //        {
                //            birth = "BIRTH=NOTSET";
                //        }
                //    }
                //    catch (Exception exe)
                //    {
                //        birth = "BIRTH=NOTSET";
                //    }

                //    good.WriteLine(name);
                //    good.WriteLine(birth);
                //    good.WriteLine(death);
                //}
                #endregion
                #region BC_a_BCE_bez_C.
                //try
                //{
                //    death = death.Split('=')[1];
                //    birth = birth.Split('=')[1];
                //    if (birth.Equals(""))
                //    {
                //        birth = "BIRTH=NOTSET";
                //    }
                //    else
                //    {
                //        if (birth.Split(' ').Count() == 2)
                //        {
                //            if (birth.Split(' ')[1].Equals("BC") || birth.Split(' ')[1].Equals("BCE") || birth.Split(' ')[1].Equals("BC."))
                //            {
                //                birth = "BIRTH=-" + birth.Split(' ')[0] + "|0|0";
                //            }
                //            else birth = "BIRTH=" + birth;
                //        }
                //        else birth = "BIRTH=" + birth;
                //    }
                //    if (death.Equals(""))
                //    {
                //        death = "DEATH=NOTSET";
                //    }
                //    else
                //    {
                //        if (death.Split(' ').Count() == 2)
                //        {
                //            if (death.Split(' ')[1].Equals("BC") || death.Split(' ')[1].Equals("BCE") || death.Split(' ')[1].Equals("BC."))
                //            {
                //                death = "DEATH=-" + death.Split(' ')[0] + "|0|0";
                //            }
                //            else death = "DEATH=" + death;
                //        }
                //        else death = "DEATH=" + death;
                //    }
                //    if ((birth.Contains('|') || birth.Equals("BIRTH=NOTSET")) && (death.Contains('|') || death.Equals("DEATH=NOTSET")))
                //    {
                //        good.WriteLine(name);
                //        good.WriteLine(birth);
                //        good.WriteLine(death);
                //    }
                //    else
                //    {
                //        bad.WriteLine(name);
                //        bad.WriteLine(birth);
                //        bad.WriteLine(death);
                //    }
                //}

                //catch (Exception e)
                //{
                //    try
                //    {
                //        string pom = death.Split('=')[1];
                //        if (pom.Equals(""))
                //        {
                //            death = "DEATH=NOTSET";
                //        }
                //    }
                //    catch (Exception ex)
                //    {
                //        death = "DEATH=NOTSET";
                //    }
                //    try
                //    {
                //        var pom = birth.Split('=')[1];
                //        if (pom.Equals(""))
                //        {
                //            birth = "BIRTH=NOTSET";
                //        }
                //    }
                //    catch (Exception exe)
                //    {
                //        birth = "BIRTH=NOTSET";
                //    }

                //    good.WriteLine(name);
                //    good.WriteLine(birth);
                //    good.WriteLine(death);
                //}
                #endregion
                #region LEN_ROKY
                //try
                //{
                //    death = death.Split('=')[1];
                //    birth = birth.Split('=')[1];
                //    if (birth.Equals(""))
                //    {
                //        birth = "BIRTH=NOTSET";
                //    }
                //    else
                //    {
                //        int year = 0;
                //        if (int.TryParse(birth, out year))
                //        {
                //            birth = "BIRTH=" + year + "|0|0";
                //        }
                //        else birth = "BIRTH=" + birth;
                //    }
                //    if (death.Equals(""))
                //    {
                //        death = "DEATH=NOTSET";
                //    }
                //    else
                //    {
                //        int year = 0;
                //        if (int.TryParse(death, out year))
                //        {
                //            death = "DEATH=" + year + "|0|0";
                //        }
                //        else death = "DEATH=" + death;
                //    }
                //    if ((birth.Contains('|') || birth.Equals("BIRTH=NOTSET")) && (death.Contains('|') || death.Equals("DEATH=NOTSET")))
                //    {
                //        good.WriteLine(name);
                //        good.WriteLine(birth);
                //        good.WriteLine(death);
                //    }
                //    else
                //    {
                //        bad.WriteLine(name);
                //        bad.WriteLine(birth);
                //        bad.WriteLine(death);
                //    }
                //}

                //catch (Exception e)
                //{
                //    try
                //    {
                //        string pom = death.Split('=')[1];
                //        if (pom.Equals(""))
                //        {
                //            death = "DEATH=NOTSET";
                //        }
                //    }
                //    catch (Exception ex)
                //    {
                //        death = "DEATH=NOTSET";
                //    }
                //    try
                //    {
                //        var pom = birth.Split('=')[1];
                //        if (pom.Equals(""))
                //        {
                //            birth = "BIRTH=NOTSET";
                //        }
                //    }
                //    catch (Exception exe)
                //    {
                //        birth = "BIRTH=NOTSET";
                //    }
                //    good.WriteLine(name);
                //    good.WriteLine(birth);
                //    good.WriteLine(death);
                //}
                #endregion
                #region BC s C.
                //try
                //{
                //    death = death.Split('=')[1];
                //    birth = birth.Split('=')[1];
                //    if (birth.Equals(""))
                //    {
                //        birth = "BIRTH=NOTSET";
                //    }
                //    else
                //    {
                //        if (birth.Split(' ').Count() ==3)
                //        {
                //            if (birth.Split(' ')[0].Equals("C.") && (birth.Split(' ')[2].Equals("BC") || birth.Split(' ')[2].Equals("BCE") || birth.Split(' ')[2].Equals("BC.")))
                //            {
                //                birth = "BIRTH=-" + birth.Split(' ')[1] + "|0|0";
                //            }
                //            else birth = "BIRTH=" + birth;
                //        }
                //        else birth = "BIRTH=" + birth;
                //    }
                //    if (death.Equals(""))
                //    {
                //        death = "DEATH=NOTSET";
                //    }
                //    else
                //    {
                //        if (death.Split(' ').Count() == 3)
                //        {
                //            if (death.Split(' ')[0].Equals("C.") && (death.Split(' ')[2].Equals("BC") || death.Split(' ')[2].Equals("BCE") || death.Split(' ')[2].Equals("BC.")))
                //            {
                //                death = "DEATH=-" + death.Split(' ')[1] + "|0|0";
                //            }
                //            else death = "DEATH=" + death;
                //        }
                //        else death = "DEATH=" + death;
                //    }
                //    if ((birth.Contains('|') || birth.Equals("BIRTH=NOTSET")) && (death.Contains('|') || death.Equals("DEATH=NOTSET")))
                //    {
                //        good.WriteLine(name);
                //        good.WriteLine(birth);
                //        good.WriteLine(death);
                //    }
                //    else
                //    {
                //        bad.WriteLine(name);
                //        bad.WriteLine(birth);
                //        bad.WriteLine(death);
                //    }
                //}

                //catch (Exception e)
                //{
                //    try
                //    {
                //        string pom = death.Split('=')[1];
                //        if (pom.Equals(""))
                //        {
                //            death = "DEATH=NOTSET";
                //        }
                //    }
                //    catch (Exception ex)
                //    {
                //        death = "DEATH=NOTSET";
                //    }
                //    try
                //    {
                //        var pom = birth.Split('=')[1];
                //        if (pom.Equals(""))
                //        {
                //            birth = "BIRTH=NOTSET";
                //        }
                //    }
                //    catch (Exception exe)
                //    {
                //        birth = "BIRTH=NOTSET";
                //    }

                //    good.WriteLine(name);
                //    good.WriteLine(birth);
                //    good.WriteLine(death);
                //}
                #endregion
                #region C. bez BC
                try
                {
                    death = death.Split('=')[1];
                    birth = birth.Split('=')[1];
                    if (birth.Equals(""))
                    {
                        birth = "BIRTH=NOTSET";
                    }
                    else
                    {
                        if (birth.Split(' ').Count() == 2)
                        {
                            if (birth.Split(' ')[0].Equals("C."))
                            {
                                birth = "BIRTH=" + birth.Split(' ')[1] + "|0|0";
                            }
                            else birth = "BIRTH=" + birth;
                        }
                        else birth = "BIRTH=" + birth;
                    }
                    if (death.Equals(""))
                    {
                        death = "DEATH=NOTSET";
                    }
                    else
                    {
                        if (death.Split(' ').Count() == 3)
                        {
                            if (death.Split(' ')[0].Equals("C."))
                            {
                                death = "DEATH=" + death.Split(' ')[1] + "|0|0";
                            }
                            else death = "DEATH=" + death;
                        }
                        else death = "DEATH=" + death;
                    }
                    if ((birth.Contains('|') || birth.Equals("BIRTH=NOTSET")) && (death.Contains('|') || death.Equals("DEATH=NOTSET")))
                    {
                        good.WriteLine(name);
                        good.WriteLine(birth);
                        good.WriteLine(death);
                    }
                    else
                    {
                        bad.WriteLine(name);
                        bad.WriteLine(birth);
                        bad.WriteLine(death);
                    }
                }
                catch (Exception e)
                {
                    try
                    {
                        string pom = death.Split('=')[1];
                        if (pom.Equals(""))
                        {
                            death = "DEATH=NOTSET";
                        }
                    }
                    catch (Exception ex)
                    {
                        death = "DEATH=NOTSET";
                    }
                    try
                    {
                        var pom = birth.Split('=')[1];
                        if (pom.Equals(""))
                        {
                            birth = "BIRTH=NOTSET";
                        }
                    }
                    catch (Exception exe)
                    {
                        birth = "BIRTH=NOTSET";
                    }
                    good.WriteLine(name);
                    good.WriteLine(birth);
                    good.WriteLine(death);
                }
                #endregion
                #region remove UKNOWN REPLACE WITH NOTSET
                //try
                //{
                //    death = death.Split('=')[1];
                //    birth = birth.Split('=')[1];
                //    if (birth.Equals(""))
                //    {
                //        birth = "BIRTH=NOTSET";
                //    }
                //    else
                //    {
                //        if (birth.Equals("UNKNOWN"))
                //        {
                //            birth = "BIRTH=NOTSET";
                //        }
                //        else birth = "BIRTH=" + birth;
                //    }
                //    if (death.Equals(""))
                //    {
                //        death = "DEATH=NOTSET";
                //    }
                //    else
                //    {
                //        if (death.Equals("UNKNOWN"))
                //        {
                //            death = "DEATH=NOTSET";
                //        }
                //        else death = "DEATH=" + death;
                //    }
                //    if ((birth.Contains('|') || birth.Equals("BIRTH=NOTSET")) && (death.Contains('|') || death.Equals("DEATH=NOTSET")))
                //    {
                //        good.WriteLine(name);
                //        good.WriteLine(birth);
                //        good.WriteLine(death);
                //    }
                //    else
                //    {
                //        bad.WriteLine(name);
                //        bad.WriteLine(birth);
                //        bad.WriteLine(death);
                //    }
                //}

                //catch (Exception e)
                //{
                //    try
                //    {
                //        string pom = death.Split('=')[1];
                //        if (pom.Equals(""))
                //        {
                //            death = "DEATH=NOTSET";
                //        }
                //    }
                //    catch (Exception ex)
                //    {
                //        death = "DEATH=NOTSET";
                //    }
                //    try
                //    {
                //        var pom = birth.Split('=')[1];
                //        if (pom.Equals(""))
                //        {
                //            birth = "BIRTH=NOTSET";
                //        }
                //    }
                //    catch (Exception exe)
                //    {
                //        birth = "BIRTH=NOTSET";
                //    }

                //    good.WriteLine(name);
                //    good.WriteLine(birth);
                //    good.WriteLine(death);
                //}
                #endregion
                line =file.ReadLine();
#region parse
                //string name = line.Replace("\r\n", "\n");
                //good.WriteLine(name);
                //line = file.ReadLine();
                ////try
                ////{
                ////    good.WriteLine(line.Replace(',',' '));

                ////}
                ////catch (Exception e)
                ////{
                ////    good.WriteLine(line);
                ////}
                ////line = file.ReadLine();
                //toto robilo to aby boli zasebou stale MENO NARODENIE A SMRT
                //if(line.Substring(0,4).Equals("NAME"))
                //{
                //    line2 = file.ReadLine();
                //    if(line2.Substring(0,5).Equals("BIRTH"))
                //    {
                //        line3 = file.ReadLine();
                //        if (line3.Substring(0, 5).Equals("DEATH"))
                //        {
                //            good.WriteLine(line);
                //            good.WriteLine(line2);
                //            good.WriteLine(line3);
                //            line = file.ReadLine();
                //            continue;
                //        }
                //        else
                //        {
                //            bad.WriteLine(line);
                //            bad.WriteLine(line2);
                //            line = line3;
                //            continue;
                //        }
                //    }
                //    else
                //    {
                //        bad.WriteLine(line);
                //         line = line2;
                //         continue;
                //    }
                //}
                //bad.WriteLine(line);
                //line = file.ReadLine();
#endregion   
            }
            good.Flush();
            good.Close();
            bad.Flush();
            bad.Close();
        }
        public static int StringToMonth(string month)
        {
            int number=-1;
            switch (month)
            {
                case "JANUARY":
                    number = 1;
                    break;
                case "FEBRUARY":
                    number = 2;
                    break;
                case "MARCH":
                    number = 3;
                    break;
                case "APRIL":
                    number = 4;
                    break;
                case "MAY":
                    number = 5;
                    break;
                case "JUNE":
                    number = 6;
                    break;
                case "JULY":
                    number = 7;
                    break;
                case "AUGUST":
                    number = 8;
                    break;
                case "SEPTEMBER":
                    number = 9;
                    break;
                case "OCTOBER":
                case "OKTOBER":
                    number = 10;
                    break;
                case "NOVEMBER":
                    number = 11;
                    break;
                case "DECEMBER":
                    number = 12;
                    break;
            }
            return number;
        }
    }
}
