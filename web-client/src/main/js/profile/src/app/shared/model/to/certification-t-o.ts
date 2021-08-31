export interface CertificationTO {
  id: number | null;
  name: string;
  issuedBy: string;
  date: string;
  expirationDate: string | null;
}
