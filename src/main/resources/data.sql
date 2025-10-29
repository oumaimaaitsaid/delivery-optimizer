-- Warehouse
MERGE INTO WAREHOUSE (id, name, latitude, longitude, openingHours) VALUES
  (1, 'Depot Central', 33.5700, -7.6200, '06:00-22:00');

-- Vehicles
MERGE INTO VEHICLE (id, type, maxWeight, maxVolume, maxDeliveries) VALUES
  (1, 'BIKE', 30, 0.15, 8),
  (2, 'VAN', 800, 6, 60),
  (3, 'TRUCK', 5000, 40, 120);

-- Deliveries (autour du dépôt)
MERGE INTO DELIVERY (id, latitude, longitude, weight, volume, timeWindow, address, preferredTimeSlot, status, tour_id) VALUES
  (100, 33.5710, -7.6210, 2.5, 0.02, '09:00-12:00', 'Client A', '10:00-11:00', 'PENDING', NULL),
  (101, 33.5725, -7.6195, 5.0, 0.04, '08:00-10:00', 'Client B', '09:00-09:30', 'PENDING', NULL),
  (102, 33.5695, -7.6180, 7.0, 0.06, '11:00-14:00', 'Client C', '12:00-13:00', 'PENDING', NULL),
  (103, 33.5735, -7.6220, 10.0, 0.08, '10:00-12:00', 'Client D', '11:00-11:30', 'PENDING', NULL),
  (104, 33.5685, -7.6215, 3.0, 0.03, '13:00-16:00', 'Client E', '14:00-15:00', 'PENDING', NULL),
  (105, 33.5745, -7.6230, 4.0, 0.03, '09:00-11:00', 'Client F', '09:30-10:30', 'PENDING', NULL),
  (106, 33.5755, -7.6240, 6.0, 0.05, '15:00-17:00', 'Client G', '16:00-16:30', 'PENDING', NULL),
  (107, 33.5665, -7.6170, 8.0, 0.07, '08:00-12:00', 'Client H', '10:30-11:30', 'PENDING', NULL),
  (108, 33.5675, -7.6160, 12.0, 0.09, '10:00-13:00', 'Client I', '11:00-12:00', 'PENDING', NULL),
  (109, 33.5760, -7.6250, 1.5, 0.01, '14:00-18:00', 'Client J', '15:00-16:00', 'PENDING', NULL),
  (110, 33.5715, -7.6260, 2.0, 0.02, '09:00-10:30', 'Client K', '09:10-09:40', 'PENDING', NULL),
  (111, 33.5720, -7.6270, 3.5, 0.03, '10:00-12:30', 'Client L', '10:30-11:00', 'PENDING', NULL),
  (112, 33.5680, -7.6280, 4.5, 0.04, '11:00-13:00', 'Client M', '12:00-12:30', 'PENDING', NULL),
  (113, 33.5690, -7.6290, 5.5, 0.05, '12:00-14:00', 'Client N', '13:00-13:30', 'PENDING', NULL),
  (114, 33.5700, -7.6300, 7.5, 0.06, '13:00-15:00', 'Client O', '14:00-14:30', 'PENDING', NULL),
  (115, 33.5710, -7.6310, 9.5, 0.08, '14:00-16:00', 'Client P', '15:00-15:30', 'PENDING', NULL),
  (116, 33.5720, -7.6320, 11.5, 0.09, '15:00-17:00', 'Client Q', '16:00-16:30', 'PENDING', NULL),
  (117, 33.5730, -7.6330, 13.5, 0.10, '08:00-09:30', 'Client R', '08:30-09:00', 'PENDING', NULL),
  (118, 33.5740, -7.6340, 15.5, 0.12, '09:30-11:00', 'Client S', '10:00-10:30', 'PENDING', NULL),
  (119, 33.5750, -7.6350, 2.5, 0.02, '11:00-12:30', 'Client T', '11:30-12:00', 'PENDING', NULL);
