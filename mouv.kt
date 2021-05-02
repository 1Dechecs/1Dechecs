fun main(val d : Case, var a : Case) {    //Case d = de départ, case a = d'arrivée
  if d.piece == Roi_Blanc     //Mouvement du roi
    if Int(d) == Int(a+1) || Int(d) == Int(a-1)
      a.piece = Roi_Blanc
      d.piece = null
    else
      main()
  if d.piece == Cavalier_Blanc    //Mouvement du cavalier
    if Int(d) == Int(a+2) || Int(d) == Int(a-2)
      a.piece = Cavalier_Blanc
      d.piece = null
    else
      main()
  if d.piece == Toure_Blanc   //Mouvement de la toure
    var t = true
    for j in [Int(a), Int(d)]   //Cases intermédiaires vides
      if j.piece != null
        t = false
      if t
        a.piece = Toure_Blanc
    		d.piece = null
    	else
    		main()
  else
    	main()
        
}

fun fin_partie() {    //Si le roi est pris, fin de partie
    while ...
}
