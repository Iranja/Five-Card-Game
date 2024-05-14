export interface Game {
  id?: number; // Ou tout autre type approprié pour l'ID du jeu
  user_id: number; // ID de l'utilisateur associé à ce jeu
  place: string; // Lieu du jeu
  points: number; // Points obtenus dans le jeu
  date: Date; // Date du jeu
  time: string; // Heure du jeu (sous forme de chaîne pour simplifier)
}
